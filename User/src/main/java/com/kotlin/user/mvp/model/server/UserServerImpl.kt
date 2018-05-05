package com.kotlin.user.mvp.model.server

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.UserApi
import com.kotlin.user.mvp.model.request.UserLoginRequest
import com.kotlin.user.mvp.model.request.UserRegisterRequest
import com.kotlin.user.mvp.model.response.UserLoginRespons
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by caofu on 2018/5/4.
 */
class UserServerImpl: UserServer {




    override fun register(email:String, pwd:String, code:String): Observable<BaseResponse<String>> {


        return RetrofitManager.mInstance.create(UserApi::class.java)
                .register(UserRegisterRequest(email, pwd, code))


    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResponse<UserLoginRespons>> {
       return RetrofitManager.mInstance.create(UserApi::class.java)
                .login(UserLoginRequest(mobile, pwd, pushId))
    }
}