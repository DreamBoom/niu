package com.yfkj.myox.adapter

import android.app.Activity
import android.content.Intent
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yfkj.myox.R
import com.yfkj.myox.activity.Add
import com.yfkj.myox.activity.Look
import com.yfkj.myox.activity.MainActivity
import com.yfkj.myox.bean.FileListBean
import com.yfkj.myox.utils.LogUtils
import kotlinx.android.synthetic.main.file_item.view.*


/**
 * @author wmx
 */
class FileAdapter(
    private val act: Activity,
    layoutResId: Int,
    data: List<FileListBean.ResultBean?>?
) :
    BaseQuickAdapter<FileListBean.ResultBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, data: FileListBean.ResultBean) {
        holder.setText(R.id.file_name, data.typeName)
        holder.getView<LinearLayout>(R.id.ll1).setOnClickListener {
            MainActivity.nameList.add("/${data.typeName}")
            val intent = Intent()
            intent.setClass(act, Add::class.java)
            intent.putExtra("name", data.typeName)
            intent.putExtra("path", data.path)
            intent.putExtra("parentId", data.parentId)
            intent.putExtra("id", data.id)
            act.startActivity(intent)
        }
        holder.getView<TextView>(R.id.look).setOnClickListener {
            search(data.typeName, data.id)
        }
    }

    private fun search(name: String, id: String) {
        MainActivity.nameList.add("/$name")
        val intent = Intent()
        intent.setClass(act, Look::class.java)
        intent.putExtra("name", name)
        intent.putExtra("id", id)
        act.startActivity(intent)

    }
}