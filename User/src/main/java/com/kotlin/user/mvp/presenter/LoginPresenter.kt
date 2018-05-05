package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.server.UserServerImpl
import com.kotlin.user.mvp.presenter.view.LoginView

/**
 * Created by caofu on 2018/5/5.
 */
class LoginPresenter :BasePresenter<LoginView>() {

    fun login(email:String, pwd:String,){



//        var impl= UserServerImpl();
//        impl.login(email,pwd,code)
//                .excute(object : BaseRxObserver<BaseResponse<String>>(){
//                    override fun success(data: BaseResponse<String>) {
//
//                        mView.onRegisterSuccess(data.data)
//
//                    }
//
//                    override fun failure(statusCode: Int, msg: String?) {
//                        mView.onRegisterFailure(statusCode,msg)
//
//                    }
//
//
//
//                })

    }

}