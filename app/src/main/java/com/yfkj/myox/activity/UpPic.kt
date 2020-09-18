package com.yfkj.myox.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import card.com.allcard.net.BaseHttpCallBack
import card.com.allcard.net.HttpRequestPort
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.TypeReference
import com.pawegio.kandroid.runAsync
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionNo
import com.yanzhenjie.permission.PermissionYes
import com.yfkj.myox.R
import com.yfkj.myox.bean.UpBean
import com.yfkj.myox.utils.BitmapUtils
import com.yfkj.myox.utils.LogUtils
import com.yfkj.myox.utils.Test
import com.yfkj.myox.view.MyGlideEngine
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import kotlinx.android.synthetic.main.up_pic.*
import yfkj.my.ActivityUtils
import java.io.File
import java.util.*

class UpPic : AppCompatActivity() {
    var utils = ActivityUtils(this)
    var chooseType = 0
    var id0 = ""
    var name0 = ""
    var pngNum = 0
    var mp4Num = 0
    var AllPath = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.up_pic)
        utils.changeStatusBlack(true, window)
        val nameList = MainActivity.nameList
        for (i in 0 until nameList.size) {
            AllPath += nameList[i]
        }
        LogUtils.i(AllPath)
        name0 = intent.getStringExtra("name")!!
        id0 = intent.getStringExtra("id")!!
        name.text = name0
        back.setOnClickListener { finish() }
        im_up.setOnClickListener {
            chooseType = 0
            AndPermission.with(this)
                .requestCode(300)
                .permission(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                // .rationale(...)
                .callback(this)
                .start()
        }
        im_up1.setOnClickListener {
            chooseType = 0
            AndPermission.with(this)
                .requestCode(300)
                .permission(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                // .rationale(...)
                .callback(this)
                .start()
        }
        video_Up.setOnClickListener {
            chooseType = 1
            AndPermission.with(this)
                .requestCode(300)
                .permission(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                // .rationale(...)
                .callback(this)
                .start()
        }
        video_Up1.setOnClickListener {
            chooseType = 1
            AndPermission.with(this)
                .requestCode(300)
                .permission(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                // .rationale(...)
                .callback(this)
                .start()
        }

        videoUp.setOnClickListener {
            val toString = fileName.text.toString()
            if (TextUtils.isEmpty(toString)) {
                utils.showToast("请输入文件名称")
                return@setOnClickListener
            }
            if (pngList.isNotEmpty()) {
                utils.getProgress(this)
                upPng()
            }
            if (mp4List.isNotEmpty()) {
                if (pngList.isEmpty()) {
                    utils.getProgress(this)
                }
                upMp4()
            }
        }
    }

    @PermissionYes(300)
    private fun getPermissionYes(grantedPermissions: List<String>) {
        if (chooseType == 0) {
            Matisse.from(this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG)) // 选择 mime 的类型
                .countable(true)
                //设置为true后，才会进入截图模式，默认为false，进入为知乎普通图片选择器
                // .cropOptions(options) //设置uCrop裁剪参数
                // .cropUri(uri)         //设置截图后的保存路径
                .maxSelectable(500) // 图片选择的最多数量
                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(1f) // 缩略图的比例
                .imageEngine(MyGlideEngine()) // 使用的图片加载引擎
                .capture(true) //是否提供拍照功能
                .captureStrategy(CaptureStrategy(true, "com.yfkj.myox.fileprovider"))//存储到哪里
                .forResult(201) // 设置作为标记的请求码
        } else {
            Matisse.from(this)
                .choose(MimeType.of(MimeType.MP4)) // 选择 mime 的类型
                .countable(true)
                //设置为true后，才会进入截图模式，默认为false，进入为知乎普通图片选择器
                // .cropOptions(options) //设置uCrop裁剪参数
                // .cropUri(uri)         //设置截图后的保存路径
                .maxSelectable(5) // 图片选择的最多数量
                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(1f) // 缩略图的比例
                .imageEngine(MyGlideEngine()) // 使用的图片加载引擎
                .capture(true) //是否提供拍照功能
                .captureStrategy(CaptureStrategy(true, "com.yfkj.myox.fileprovider"))//存储到哪里
                .forResult(202) // 设置作为标记的请求码
        }
    }

    private fun upPng() {
        if (pngNum < pngList.size) {
            val nextInt = Random().nextInt(9999)
            HttpRequestPort.instance.up(pngList[pngNum],
                fileName.text.toString() + "_" + nextInt + "_" + pngNum,
                "png",
                AllPath,
                id0,
                object : BaseHttpCallBack(this) {
                    override fun onFinished() {
                        super.onFinished()
                        pngNum += 1
                        upPng()
                    }
                })
        } else {
            pngNum = 0
            if (mp4List.size < 1) {
                utils.hindProgress()
                finish()
            }
        }
    }

    val chunk = 10 * 1024 * 1024
    private fun upMp4() {
        val nextInt = Random().nextInt(9999)
        if (mp4Num < mp4List.size) {
            val file = mp4List[mp4Num]
            val l = (file.length() / chunk + 1).toInt()
            runAsync {
                Test.fileSp(file.path, "/storage/emulated/0/DCIM/Camera/")
                upMp4Fp(l, nextInt)
            }
        } else {
            mp4Num = 0
            videoUp.text = "上传完成 再次上传"
            utils.hindProgress()
            finish()
        }
    }

    var ll = 0
    private fun upMp4Fp(long: Int, time: Int) {
        if (ll < long) {
            val file1 = File("/storage/emulated/0/DCIM/Camera/a$ll.mp4")
            HttpRequestPort.instance.mp4Up(file1,
                fileName.text.toString() + "_" + time + "_" + mp4Num + ".mp4",
                "mp4",
                id0,
                "121234",
                "" + ll,
                "" + long, object : BaseHttpCallBack(this) {
                    override fun onSuccess(s: String) {
                        super.onSuccess(s)
                        val bean =
                            JSONObject.parseObject(s, object : TypeReference<UpBean>() {})
                        if (bean.code != 200) {
                            utils.hindProgress()
                            return
                        } else {
                            deleteImage(file1.path)
                            ll++
                            upMp4Fp(long, time)
                        }
                    }
                }
            )
        } else {
            HttpRequestPort.instance.mp4Up1(
                fileName.text.toString() + "_" + time + "_" + mp4Num + ".mp4",
                "mp4",
                id0,
                "121234",
                AllPath,
                object : BaseHttpCallBack(this@UpPic) {
                    override fun onSuccess(s: String) {
                        super.onSuccess(s)
                        mp4Num++
                        upMp4()
                        ll = 0
                    }

                    override fun onError(throwable: Throwable, b: Boolean) {
                        super.onError(throwable, b)
                        utils.hindProgress()
                    }
                })
        }
    }

    @PermissionNo(300)
    private fun getPermissionNo(deniedPermissions: List<String>) {
        // 申请权限失败。
        // 是否有不再提示并拒绝的权限
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用AndPermission默认的提示语。
            //AndPermission.defaultSettingDialog(this, 400).show()
            promess()
        }
    }

    //存储权限被拒弹窗
    private fun promess() {
        val v = utils.getView(this, R.layout.pop_prossmess)
        val promess = PopupWindow(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        promess.contentView = v
        promess.setBackgroundDrawable(ColorDrawable(0x00000000))
        promess.isClippingEnabled = false
        promess.showAsDropDown(name)
        v.findViewById<TextView>(R.id.tv_2).text = "相机及存储权限被拒绝，请在设置中授权后重试"
        //存储权限被拒绝，请在设置中授权后重试
        v.findViewById<TextView>(R.id.tv_3).setOnClickListener {
            promess.dismiss()
        }
        v.findViewById<TextView>(R.id.tv_4).setOnClickListener {
            promess.dismiss()
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }
    }

    var pngList = arrayListOf<File>()
    var mp4List = arrayListOf<File>()
    var all_P_Num = 0
    var all_V_Num = 0

    @SuppressLint("Recycle", "SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 201 && resultCode == RESULT_OK) {
            pngNum = 0
            pngList.clear()
            val mSelected = Matisse.obtainResult(data)
            var bitm = BitmapUtils.uriToBitmap(this, mSelected[mSelected.size - 1])
            for (i in 0 until mSelected.size) {
                val file = uriToFile(mSelected[i], this)
                pngList.add(file!!)
            }
            if (pngList.size > 0) {
                all_P_Num += pngList.size
                im_up1.visibility = View.VISIBLE
                im_up.isEnabled = false
                pNum.visibility = View.VISIBLE
                pNum.text = "共 $all_P_Num 张"
            }
            im_up.setImageBitmap(bitm)
        }
        if (requestCode == 202 && resultCode == RESULT_OK) {
            mp4Num = 0
            mp4List.clear()
            if (resultCode == RESULT_OK) {
                val mSelected1 = Matisse.obtainResult(data)
                for (i in 0 until mSelected1.size) {
                    val uriToFile = uriToFile(mSelected1[i], this)
                    if (uriToFile!!.length() > chunk) {
                        mp4List.add(uriToFile)
                    }
                }
                if (mp4List.size < 1) {
                    utils.showToast("视频长度不够，请重新上传")
                } else {
                    all_V_Num += mp4List.size
                    video_Up1.visibility = View.VISIBLE
                    video_Up.isEnabled = false
                    vNum.text = "共 $all_V_Num 个"
                    video_Up.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.img_spt))
                }

            }
        }
    }

    private fun uriToFile(uri: Uri, context: Context): File? {
        var path: String? = null
        if ("file" == uri.scheme) {
            path = uri.encodedPath
            if (path != null) {
                path = Uri.decode(path)
                val cr: ContentResolver = context.contentResolver
                val buff = StringBuffer()
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                    .append("'$path'").append(")")
                val cur = cr.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    arrayOf<String>(
                        MediaStore.Images.ImageColumns._ID,
                        MediaStore.Images.ImageColumns.DATA
                    ),
                    buff.toString(),
                    null,
                    null
                )
                var index = 0
                var dataIdx = 0
                cur!!.moveToFirst()
                while (!cur.isAfterLast) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID)
                    index = cur.getInt(index)
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    path = cur.getString(dataIdx)
                    cur.moveToNext()
                }
                cur.close()
                if (index == 0) {
                } else {
                    val u = Uri.parse("content://media/external/images/media/$index")
                }
            }
            if (path != null) {
                return File(path)
            }
        } else if ("content" == uri.scheme) {
            // 4.2.2以后
            val proj = arrayOf<String>(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(uri, proj, null, null, null)
            if (cursor!!.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                path = cursor.getString(columnIndex)
            }
            cursor.close()
            return File(path)
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null
    }

    //删除文件后更新数据库  通知媒体库更新文件夹,！！！！！filepath（文件夹路径）要求尽量精确，以防删错
    private fun deleteImage(imgPath: String) {
        val resolver = contentResolver
        val cursor: Cursor = MediaStore.Images.Media.query(
            resolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID),
            MediaStore.Images.Media.DATA + "=?",
            arrayOf(imgPath),
            null
        )
        var result = false
        var uri: Uri? = null
        if (cursor.moveToFirst()) {
            val id: Long = cursor.getLong(0)
            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            uri = ContentUris.withAppendedId(contentUri, id)
            val count = contentResolver.delete(uri, null, null)
            result = count == 1
        } else {
            val cursor2: Cursor = MediaStore.Images.Media.query(
                resolver,
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Images.Media._ID),
                MediaStore.Images.Media.DATA + "=?",
                arrayOf(imgPath),
                null
            )
            if (cursor2.moveToFirst()) {
                val id: Long = cursor2.getLong(0)
                val contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                uri = ContentUris.withAppendedId(contentUri, id)
                val count = contentResolver.delete(uri, null, null)
                result = count == 1
            }
        }
        //更新到图库
        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(File(imgPath))
        sendBroadcast(intent)
    }

}
