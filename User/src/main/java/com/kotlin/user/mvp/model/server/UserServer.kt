package com.kotlin.user.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.user.mvp.model.response.UserInfoResponse
import io.reactivex.Observable


/**
 * Created by  on 2018/5/4.
 */
interface UserServer {

    /**
     * 注册
     */
    fun register(email:String, pwd:String, code:String): Observable<BaseResponse<String>>

    /**
     * 登录
     */
    fun login( mobile:String,  pwd:String,  pushId:String): Observable<BaseResponse<UserInfoResponse>>

    /**
     * 忘记密码
     */
    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResponse<String>>

    /**
     * 重置密码
     */
    fun resetPwd(mobile: String, pwd: String): Observable<BaseResponse<String>>


    /**
     * 编辑用户资料
     */
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String): Observable<BaseResponse<UserInfoResponse>>

}