package com.yfkj.myox.activity

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.activity_main.*
import yfkj.my.ActivityUtils
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var firstTime: Long = 0
    var utils = ActivityUtils(this)
    var dataAdapter: FileAdapter? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        var nameList = arrayListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        utils.changeStatusBlack(true, window)
        val ms = LinearLayoutManager(this)
        ms.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = ms
        r1.setOnClickListener { pop() }
        refresh.setEnableOverScrollDrag(false)
        refresh.setOnRefreshListener { getData() }
    }

    private fun pop() {
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
        val name = v.findViewById<EditText>(R.id.et_name)
        tv3!!.setOnClickListener {
            utils.hideSoftKeyboard()
            pop.dismiss()
        }
        tv4!!.setOnClickListener {
            val toString = name.text.toString()
            if (TextUtils.isEmpty(toString)) {
                utils.showToast("文件夹名称不能为空")
            } else {
                HttpRequestPort.instance.addFile(
                    "0",
                    toString,
                    "",
                    object : BaseHttpCallBack(this) {
                        override fun onSuccess(s: String) {
                            super.onSuccess(s)
                            LogUtils.i(s)
                            val bean =
                                JSONObject.parseObject(s, object : TypeReference<AddBean>() {})
                            if (bean.code == 200) {
                                refresh.autoRefresh()
                                pop.dismiss()
                            } else {
                                utils.showToast("网络异常，请重新添加")
                            }
                        }

                        override fun onFinished() {
                            super.onFinished()
                            utils.hideSoftKeyboard()
                        }
                    })

            }
        }
    }

    private fun getData() {
        HttpRequestPort.instance.fileList("0", "1", object : BaseHttpCallBack(this) {
            override fun onSuccess(s: String) {
                super.onSuccess(s)
                LogUtils.i(s)
                val bean = JSONObject.parseObject(s, object : TypeReference<FileListBean>() {})
                dataAdapter = FileAdapter(this@MainActivity, R.layout.file_item, bean.result)
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
        nameList.clear()
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_BACK) {
            val secondTime = System.currentTimeMillis()
            if (secondTime - firstTime > 2000) {
                utils.showToast("再按一次退出程序")
                firstTime = secondTime
                return false
            } else {
                exitProcess(0)
            }
        }
        return super.dispatchKeyEvent(event)
    }
}