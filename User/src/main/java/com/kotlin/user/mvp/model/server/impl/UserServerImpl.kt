package com.kotlin.user.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.UserApi
import com.kotlin.user.mvp.model.request.ForgetPwdRequest
import com.kotlin.user.mvp.model.request.ResetPwdRequest
import com.kotlin.user.mvp.model.request.UserLoginRequest
import com.kotlin.user.mvp.model.request.UserRegisterRequest
import com.kotlin.user.mvp.model.response.UserLoginResponse
import com.kotlin.user.mvp.model.server.UserServer
import io.reactivex.Observable


/**
 * Created by  on 2018/5/4.
 */
class UserServerImpl: UserServer {



    override fun register(email:String, pwd:String, code:String): Observable<BaseResponse<String>> {


        return RetrofitManager.mInstance.create(UserApi::class.java)
                .register(UserRegisterRequest(email, pwd, code))


    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResponse<UserLoginResponse>> {
       return RetrofitManager.mInstance.create(UserApi::class.java)
                .login(UserLoginRequest(mobile, pwd, pushId))
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdRequest(mobile, verifyCode))
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(UserApi::class.java)
                .resetPwd(ResetPwdRequest(mobile, pwd))
    }
}