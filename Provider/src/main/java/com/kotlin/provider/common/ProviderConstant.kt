package com.kotlin.provider.common

import android.icu.text.SimpleDateFormat
import android.net.Uri
import com.vondear.rxtools.RxFileTool
import java.io.File
import java.util.*

/*
    业务常量
 */
class ProviderConstant {
    companion object {
        //用户名称
        const val KEY_SP_USER_NAME = "sp_user_name"
        //用户电话
        const val KEY_SP_USER_MOBILE = "sp_user_mobile"
        //用户头像
        const val KEY_SP_USER_ICON = "sp_user_icon"
        //用户性别
        const val KEY_SP_USER_GENDER = "sp_user_gender"
        //用户签名
        const val KEY_SP_USER_SIGN = "sp_user_sign"

        const val KEY_SP_USER_CACHE = "sp_user_cache"
        //订单ID
        const val KEY_ORDER_ID = "order_id"
        //订单价格
        const val KEY_ORDER_PRICE = "order_price"


        //阿里云
        const val OOS_ENDPOINT = "oss-cn-hongkong.aliyuncs.com"
        const val OOS_ACCESSKEYID = "LTAIRhH2MoxuE44s"  // accessKeyId
        const val OOS_ACCESSKEYSECRET = "cguCUzUzcOt2MGENfIElGGevCh5iHV" // accessKeySecret
        const val OOS_BUCKET_NAME = "header-icon"
        /**
         *     获得照片的输出保存Uri
         */

        fun getImageCropUri(): Uri {
            val file = File(RxFileTool.getSDCardPath(), "temp/" + System.currentTimeMillis() + ".jpg")
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            return Uri.fromFile(file)
        }

        /**
         * Description: 判断OSS服务文件上传时文件的contentType
         *
         * @param FilenameExtension 文件后缀
         * @return String
         */
        fun getcontentType(path: String): String {


            var index=path.lastIndexOf(".")

            var FilenameExtension=  path.substring(index+1,path.length)

            if (FilenameExtension.equals(".bmp", ignoreCase = true)) {
                return "image/bmp"
            }
            if (FilenameExtension.equals(".gif", ignoreCase = true)) {
                return "image/gif"
            }
            if (FilenameExtension.equals(".jpeg", ignoreCase = true) ||
                    FilenameExtension.equals(".jpg", ignoreCase = true) ||
                    FilenameExtension.equals(".png", ignoreCase = true)) {
                return "image/jpeg"
            }
            if (FilenameExtension.equals(".html", ignoreCase = true)) {
                return "text/html"
            }
            if (FilenameExtension.equals(".txt", ignoreCase = true)) {
                return "text/plain"
            }
            if (FilenameExtension.equals(".vsd", ignoreCase = true)) {
                return "application/vnd.visio"
            }
            if (FilenameExtension.equals(".pptx", ignoreCase = true) || FilenameExtension.equals(".ppt", ignoreCase = true)) {
                return "application/vnd.ms-powerpoint"
            }
            if (FilenameExtension.equals(".docx", ignoreCase = true) || FilenameExtension.equals(".doc", ignoreCase = true)) {
                return "application/msword"
            }
            return if (FilenameExtension.equals(".xml", ignoreCase = true)) {
                "text/xml"
            } else "image/jpeg"
        }
        
    }

}
