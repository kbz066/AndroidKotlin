package com.kotlin.user.mvp.model.server.impl

import android.content.Context
import com.alibaba.sdk.android.oss.model.ObjectMetadata
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.kotlin.provider.utils.OSSUtils
import com.kotlin.user.mvp.model.server.UploadImageServer
import io.reactivex.Observable

/**
 * Created by  on 2018/5/11.
 */
class UploadImageServerImpl: UploadImageServer {
    override fun uploadHeaderIcon(mContext: Context, testBucket: String, oosPath: String, locationPath: String): Observable<PutObjectResult> {


        return Observable.create{



            var result:PutObjectResult=OSSUtils.uploadHeaderIcon(mContext,testBucket, oosPath, locationPath)
            it.onNext(result)
        }

    }


}