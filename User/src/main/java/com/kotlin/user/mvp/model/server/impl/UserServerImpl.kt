package com.kotlin.user.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.UserApi
import com.kotlin.user.mvp.model.request.*
import com.kotlin.user.mvp.model.response.UserInfoResponse
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

    override fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResponse<UserInfoResponse>> {
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
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<BaseResponse<UserInfoResponse>> {
        return RetrofitManager.mInstance.create(UserApi::class.java)
                .editUser(EditUserRequest(userIcon, userName,userGender,userSign))
    }
}