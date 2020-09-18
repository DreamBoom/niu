package card.com.allcard.net

import java.io.File
import java.util.*

/**
 * @author Created by Dream
 * 网络接口
 */
class HttpRequestPort {
    companion object {
//        const val BASE_URL = "http://192.168.12.10:8080/hnyfkj-file-server/"
//        const val IM_URL = "http://192.168.12.10:8080/"
        const val BASE_URL = "http://115.56.231.22:9003/hnyfkj-file-server/"
        const val IM_URL = "http://115.56.231.22:9003/"
        private var httpRequestPort: HttpRequestPort? = null

        val instance: HttpRequestPort
            get() {
                if (httpRequestPort == null) {
                    httpRequestPort = HttpRequestPort()
                }
                return httpRequestPort as HttpRequestPort
            }
    }

    private val fileList = "industrytype/selectListDto"
    private val addFile = "industrytype/insert"
    private val upFile = "industrytype/update"
    private val up = "common/uploadVirtual"
    private val look = "industrytype/selectList"

    //common/upload
    private val mp4Up = "common/upload"
    private val mp4Up1 = "common/merger"

    private val httpUtil: HttpUtil = HttpUtil()
    private var map: MutableMap<String, String>? = null

    /**文件列表 */
    fun fileList(parentId: String, page: String, callBack: BaseHttpCallBack) {
        map = HashMap()
        map!!["parentId"] = parentId
        map!!["page"] = page
        map!!["limit"] = "1000"
        httpUtil.post(BASE_URL + fileList, map, callBack)
    }

    /**文件添加 */
    fun addFile(parentId: String, typeName: String, path: String, callBack: BaseHttpCallBack) {
        map = HashMap()
        map!!["parentId"] = parentId
        map!!["typeName"] = typeName
        map!!["path"] = path
        httpUtil.post(BASE_URL + addFile, map, callBack)
    }

    /**文件修改 */
    fun upFile(
        id: String,
        parentId: String,
        typeName: String,
        path: String,
        callBack: BaseHttpCallBack
    ) {
        map = HashMap()
        map!!["id"] = id
        map!!["parentId"] = parentId
        map!!["typeName"] = typeName
        map!!["path"] = path
        httpUtil.post(BASE_URL + upFile, map, callBack)
    }

    /**文件查看 */
    fun look(
        vid: String,
        page: String,
        callBack: BaseHttpCallBack
    ) {
        map = HashMap()
        map!!["vId"] = vid
        map!!["page"] = page
        map!!["limit"] = "10"
        httpUtil.post(BASE_URL + look, map, callBack)
    }

    /**文件上传*/
    fun up(
        file: File,
        name: String,
        type: String,
        virtualUrl: String,
        path: String,
        callBack: BaseHttpCallBack
    ) {
        httpUtil.upload(BASE_URL + up, file, name, type, path, virtualUrl, callBack)
    }

    /**视频上传*/
    fun mp4Up(
        file: File,
        name: String,
        type: String,
        virtualUrl: String,
        md5: String,
        chunk: String,
        chunks: String,
        callBack: BaseHttpCallBack
    ) {
        httpUtil.upMp4(BASE_URL + mp4Up, file, name, type, virtualUrl, md5, chunk, chunks, callBack)
    }

    /**视频上传*/
    fun mp4Up1(
        name: String,
        type: String,
        virtualUrl: String,
        md5: String,
        path: String,
        callBack: BaseHttpCallBack
    ) {
        map = HashMap()
        map!!["name"] = name
        map!!["type"] = type
        map!!["virtualUrl"] = virtualUrl
        map!!["md5"] = md5
        map!!["path"] = path
        map!!["system"] = "system"
        httpUtil[BASE_URL + mp4Up1, map, callBack]
    }
}