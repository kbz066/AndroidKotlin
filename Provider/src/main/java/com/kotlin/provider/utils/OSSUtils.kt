package com.kotlin.provider.utils

import android.content.Context
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider
import com.kotlin.provider.common.ProviderConstant
import com.alibaba.sdk.android.oss.common.OSSLog
import org.apache.http.params.HttpConnectionParams.setConnectionTimeout
import com.alibaba.sdk.android.oss.ClientConfiguration
import com.alibaba.sdk.android.oss.OSS
import com.alibaba.sdk.android.oss.OSSClient
import com.kotlin.base.common.BaseApplication
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult


/**
 * Created by  on 2018/5/15.
 */
object OSSUtils {

    fun initOOS(mContext:Context):OSS{
        val conf = ClientConfiguration()
        conf.connectionTimeout = 15 * 1000 // 连接超时，默认15秒
        conf.socketTimeout = 15 * 1000 // socket超时，默认15秒
        conf.maxConcurrentRequest = 5 // 最大并发请求书，默认5个
        conf.maxErrorRetry = 2 // 失败后最大重试次数，默认2次
        OSSLog.enableLog()
        val credentialProvider by lazy { OSSPlainTextAKSKCredentialProvider(ProviderConstant.OOS_ACCESSKEYID,ProviderConstant.OOS_ACCESSKEYSECRET) }
        return OSSClient(mContext, ProviderConstant.OOS_ENDPOINT, credentialProvider, conf)
    }

    /**
     * 上传图片
     */
    fun uploadHeaderIcon(mContext:Context,testBucket:String, oosPath:String, locationPath:String): PutObjectResult {

       return initOOS(mContext).putObject(PutObjectRequest(testBucket, oosPath, locationPath))
    }


}