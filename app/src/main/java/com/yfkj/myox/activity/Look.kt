package com.yfkj.myox.activity

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import card.com.allcard.net.BaseHttpCallBack
import card.com.allcard.net.HttpRequestPort
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.TypeReference
import com.yfkj.myox.R
import com.yfkj.myox.adapter.GridAdapter
import com.yfkj.myox.bean.LookBean
import com.yfkj.myox.utils.LogUtils
import kotlinx.android.synthetic.main.activity_look.*
import yfkj.my.ActivityUtils

class Look : AppCompatActivity() {
    var utils = ActivityUtils(this)
    var list = arrayListOf<LookBean.ResultBean.ListBean>()
    var gridAdapter: GridAdapter? = null
    private var page = 1
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look)
        utils.changeStatusBlack(true, window)
        val na = intent.getStringExtra("name")
        id = intent.getStringExtra("id")!!
        name.text = na
        back.setOnClickListener {
            if (MainActivity.nameList.size > 0) {
                MainActivity.nameList.removeAt(MainActivity.nameList.size - 1)
            }
            finish()
        }

        gridAdapter = GridAdapter(this@Look, list, R.layout.look_item)
        grid.adapter = gridAdapter
        up.setOnClickListener {
            intent.setClass(this@Look, UpPic::class.java)
            intent.putExtra("name", na)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        refresh.setEnableOverScrollDrag(false)
        refresh.setOnRefreshListener {
            page = 1
            init(id)
        }
        refresh.setOnLoadMoreListener { init(id) }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (MainActivity.nameList.size > 0) {
                MainActivity.nameList.removeAt(MainActivity.nameList.size - 1)
            }
            finish()
            false
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    private fun init(id: String) {
        HttpRequestPort.instance.look(
            id,
            "" + page,
            object : BaseHttpCallBack(this) {
                override fun onSuccess(s: String) {
                    super.onSuccess(s)
                    val bean =
                        JSONObject.parseObject(s, object : TypeReference<LookBean>() {})
                    if (bean.code == 200) {
                        if (bean.result.list.size > 0) {
                            if (page == 1) {
                                list.clear()
                            }
                            list.addAll(bean.result.list)
                            gridAdapter!!.notifyDataSetChanged()
                            if(page>1){
                                grid.smoothScrollToPosition((page-1)*10-2)
                            }
                            page++
                        } else {
                            utils.showToast("暂无文件")
                        }
                    }
                }

                override fun onFinished() {
                    super.onFinished()
                    refresh.finishRefresh()
                    refresh.finishLoadMore()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        refresh.autoRefresh()
    }
}