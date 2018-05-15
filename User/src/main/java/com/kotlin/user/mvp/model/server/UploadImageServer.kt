package com.kotlin.user.mvp.model.server

import android.content.Context
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.kotlin.provider.utils.OSSUtils
import io.reactivex.Observable

/**
 * Created by  on 2018/5/11.
 */
interface UploadImageServer {

    /**
     * 上传图片
     */
    fun uploadHeaderIcon(mContext: Context, testBucket:String, oosPath:String, locationPath:String): Observable<PutObjectResult>;
}