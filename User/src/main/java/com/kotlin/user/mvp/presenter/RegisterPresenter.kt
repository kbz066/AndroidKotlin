package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.server.UserServer
import com.kotlin.user.mvp.presenter.view.RegisterView
import javax.inject.Inject


class RegisterPresenter @Inject constructor():BasePresenter<RegisterView>() {



    @Inject
    lateinit var mUserServer: UserServer





    fun register( email:String, pwd:String, code:String){


        mUserServer.register(email,pwd,code)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {

                        mView.onRegisterSuccess(data.data)

                    }

                    override fun failure(statusCode: Int, msg: String?) {
                        mView.onError(statusCode,msg)

                    }



                },rxLifecycle)



    }


}