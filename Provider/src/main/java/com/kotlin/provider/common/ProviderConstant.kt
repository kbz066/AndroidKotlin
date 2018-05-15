package com.kotlin.provider.common

import android.icu.text.SimpleDateFormat
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
        const val OOS_ENDPOINT ="oss-cn-hongkong.aliyuncs.com"
        const val OOS_ACCESSKEYID = "LTAIRhH2MoxuE44s"  // accessKeyId
        const val OOS_ACCESSKEYSECRET = "cguCUzUzcOt2MGENfIElGGevCh5iHV" // accessKeySecret
        const val OOS_BUCKET_NAME="header-icon"

        /**
         *
         * @return 图片上传到阿里云的路径
         */
//        fun getPhotoFileName(): String {
//            val date = Date(System.currentTimeMillis())
//            val dateFormat = SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault())
//            return "img" + "/" + dateFormat.format(date) + ".webp"
//        }
    }


}
