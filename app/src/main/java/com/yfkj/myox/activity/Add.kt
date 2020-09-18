package com.yfkj.myox.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import card.com.allcard.net.BaseHttpCallBack
import card.com.allcard.net.HttpRequestPort
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.TypeReference
import com.yfkj.myox.R
import com.yfkj.myox.adapter.FileAdapter
import com.yfkj.myox.bean.AddBean
import com.yfkj.myox.bean.FileListBean
import com.yfkj.myox.utils.LogUtils
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.refresh
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.list
import kotlinx.android.synthetic.main.activity_main.name
import kotlinx.android.synthetic.main.activity_main.r1
import yfkj.my.ActivityUtils

class Add : AppCompatActivity() {
    var utils = ActivityUtils(this)
    var dataAdapter: FileAdapter? = null
    var parentId = ""
    var id = ""
    var path = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        utils.changeStatusBlack(true, window)
        back.setOnClickListener {
            if (MainActivity.nameList.size > 0) {
                MainActivity.nameList.removeAt(MainActivity.nameList.size - 1)
            }
            finish()
        }
        change.setOnClickListener { pop(1) }
        val na = intent.getStringExtra("name")
        parentId = intent.getStringExtra("parentId")!!
        id = intent.getStringExtra("id")!!
        path = intent.getStringExtra("path")!!
        name.text = na
        utils.changeStatusBlack(true, window)
        val ms = LinearLayoutManager(this)
        ms.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = ms
        r1.setOnClickListener { pop(0) }
        refresh.setEnableOverScrollDrag(false)
        refresh.setOnRefreshListener { getData() }
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

    private fun pop(type: Int) {
        val v = utils.getView(this, R.layout.pop_edit)
        val pop = PopupWindow(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        pop.contentView = v
        pop.setBackgroundDrawable(ColorDrawable(0x00000000))
        pop.isFocusable = true
        pop.isClippingEnabled = true
        pop.showAsDropDown(name)
        val tv3 = v.findViewById<TextView>(R.id.tv_3)
        val tv4 = v.findViewById<TextView>(R.id.tv_4)
        val etName = v.findViewById<EditText>(R.id.et_name)
        tv3!!.setOnClickListener { pop.dismiss() }
        tv4!!.setOnClickListener {
            val toString = etName.text.toString()
            if (TextUtils.isEmpty(toString)) {
                utils.showToast("文件夹名称不能为空")
            } else {
                if (type == 0) {
                    HttpRequestPort.instance.addFile(
                        id,
                        toString,
                        path,
                        object : BaseHttpCallBack(this) {
                            override fun onSuccess(s: String) {
                                super.onSuccess(s)
                                LogUtils.i(s)
                                utils.hideSoftKeyboard()
                                val bean =
                                    JSONObject.parseObject(s, object : TypeReference<AddBean>() {})
                                if (bean.code == 200) {
                                    refresh.autoRefresh()
                                    pop.dismiss()
                                } else {
                                    utils.showToast(bean.msg)
                                }
                            }
                        })
                } else {
                    HttpRequestPort.instance.upFile(
                        id,
                        parentId,
                        toString,
                        path,
                        object : BaseHttpCallBack(this) {
                            override fun onSuccess(s: String) {
                                super.onSuccess(s)
                                utils.hideSoftKeyboard()
                                val bean =
                                    JSONObject.parseObject(s, object : TypeReference<AddBean>() {})
                                if (bean.code == 200) {
                                    name.text = toString
                                    MainActivity.nameList.removeAt(MainActivity.nameList.size - 1)
                                    MainActivity.nameList.add("/$toString")
                                    pop.dismiss()
                                } else {
                                    utils.showToast("网络异常，请重新添加")
                                }
                            }
                        })
                }
            }
        }
    }

    private fun getData() {
        HttpRequestPort.instance.fileList(id, "1", object : BaseHttpCallBack(this) {
            override fun onSuccess(s: String) {
                super.onSuccess(s)
                val bean = JSONObject.parseObject(s, object : TypeReference<FileListBean>() {})
                dataAdapter = FileAdapter(this@Add, R.layout.file_item, bean.result)
                list.adapter = dataAdapter
            }

            override fun onFinished() {
                super.onFinished()
                refresh!!.finishRefresh()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}