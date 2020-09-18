package card.com.allcard.net

import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x
import java.io.File


/**
 * @author Created by Dream
 */
class HttpUtil {
    /**
     * get请求
     * @param url
     * @param map
     * @param callBack
     * @param <T>
     * @return
    </T> */
    operator fun <T> get(
        url: String,
        map: Map<String, String>?,
        callBack: Callback.CommonCallback<T>
    ): Callback.Cancelable {
        val params = RequestParams(url)
        if (map != null) {
            for ((key, value) in map) {
                params.addQueryStringParameter(key, value)
            }
            params.addHeader("Content-Type", "application/json;charset=UTF-8")
        }
        params.connectTimeout = 5000
        return x.http().get(params, callBack)
    }

    /**
     * post请求
     * @param url
     * @param map
     * @param callback
     * @param <T>
     * @return
    </T> */
    fun <T> post(
        url: String,
        map: Map<String, String>?,
        callback: Callback.CommonCallback<T>
    ): Callback.Cancelable {
        val params = RequestParams(url)
        if (map != null) {
            for ((key, value) in map) {
                params.addParameter(key, value)
            }
        }
        params.addHeader("Content-Type", "application/json;charset=UTF-8")
        params.connectTimeout = 5000
        return x.http().post(params, callback)
    }

    /**
     * 上传文件
     * @param url
     * @param callback
     * @param <T>
     * @return
    </T> */
    fun <T> upload(
        url: String,
        file: File,
        name: String,
        type: String,
        virtualUrl: String,
        path: String,
        callback: Callback.CommonCallback<T>
    ): Callback.Cancelable {
        val params = RequestParams(url)
        params.isMultipart = true
        params.addBodyParameter("file", file)
        params.addParameter("name", name)
        params.addParameter("type", type)
        params.addParameter("virtualUrl", virtualUrl)
        params.addParameter("path", path)
        return x.http().post(params, callback)
    }

    /**
     * 上传视频
     * @param url
     * @param callback
     * @param <T>
     * @return
    </T> */
    fun <T> upMp4(
        url: String, file: File, name: String, type: String, virtualUrl: String,
        md5: String, chunk: String, chunks: String, callback: Callback.CommonCallback<T>
    ): Callback.Cancelable {
        val params = RequestParams(url)
        params.isMultipart = true
        params.addBodyParameter("file", file)
        params.addParameter("name", name)
        params.addParameter("type", type)
        params.addParameter("virtualUrl", virtualUrl)
        params.addParameter("md5", md5)
        params.addParameter("chunk", chunk)
        params.addParameter("chunks", chunks)
        params.addParameter("system", "system")
        params.connectTimeout = 500000
        return x.http().post(params, callback)
    }

    /**
     * 上传视频
     * @param url
     * @param callback
     * @param <T>
     * @return
    </T> */
    fun <T> upMp41(
        url: String, name: String, type: String, virtualUrl: String,
        md5: String, callback: Callback.CommonCallback<T>
    ): Callback.Cancelable {
        val params = RequestParams(url)
        params.isMultipart = true
        params.addParameter("name", name)
        params.addParameter("type", type)
        params.addParameter("virtualUrl", virtualUrl)
        params.addParameter("md5", md5)
        params.addParameter("system", "system")
//        params.addHeader("Content-Type","multipart/form-data")
        params.connectTimeout = 500000
//        params.addQueryStringParameter("fixparam", "android")
        return x.http().get(params, callback)
    }

    fun download(
        url: String,
        path: String,
        callback: Callback.ProgressCallback<File>
    ): Callback.Cancelable {
        val params = RequestParams(url)
        params.saveFilePath = path
        params.isAutoRename = true
        return x.http().post(params, callback)
    }

    fun <T> postJson(url: String, json: String, callBack: BaseHttpCallBack): Callback.Cancelable {
        val params = RequestParams(url)
        params.bodyContent = json
        params.addHeader("Content-Type", "application/json;charset=UTF-8")
        return x.http().post(params, callBack)
    }
}
