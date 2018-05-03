package com.kotlin.user.mvp.model

import com.kotlin.base.Rx.BaseRxResponse
import com.kotlin.base.data.protocol.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



/**
 * Created by  on 2018/5/3.
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body request:UserRegisterRequest): Observable<BaseResponse<String>>

}