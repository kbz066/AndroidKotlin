package com.kotlin.user.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.request.*
import com.kotlin.user.mvp.model.response.UserInfoResponse
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
    fun login(@Body request: UserLoginRequest): Observable<BaseResponse<UserInfoResponse>>


    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdRequest): Observable<BaseResponse<String>>


    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req:ResetPwdRequest): Observable<BaseResponse<String>>


    @POST("userCenter/editUser")
    fun editUser(@Body req:EditUserRequest): Observable<BaseResponse<UserInfoResponse>>
}