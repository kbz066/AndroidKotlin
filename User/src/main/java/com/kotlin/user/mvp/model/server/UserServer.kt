package com.kotlin.user.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.request.UserRegisterRequest
import com.kotlin.user.mvp.model.response.UserLoginRespons
import io.reactivex.Observable
import retrofit2.http.Body

/**
 * Created by  on 2018/5/4.
 */
interface UserServer {

    /**
     * 注册
     */
    fun register(email:String, pwd:String, code:String): Observable<BaseResponse<String>>

    fun login( mobile:String,  pwd:String,  pushId:String): Observable<BaseResponse<UserLoginRespons>>
}