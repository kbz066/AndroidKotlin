package com.kotlin.user.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.request.UserLoginRequest
import com.kotlin.user.mvp.model.request.UserRegisterRequest
import com.kotlin.user.mvp.model.response.UserLoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST



/**
 * Created by  on 2018/5/3.
 */
interface UserApi {


    @POST("userCenter/register")
    fun register(@Body request: UserRegisterRequest): Observable<BaseResponse<String>>


    @POST("userCenter/login")
    fun login(@Body request: UserLoginRequest): Observable<BaseResponse<UserLoginResponse>>

}