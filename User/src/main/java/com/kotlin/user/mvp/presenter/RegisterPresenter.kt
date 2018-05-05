package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.server.UserServerImpl
import com.kotlin.user.mvp.presenter.view.RegisterView


class RegisterPresenter:BasePresenter<RegisterView>() {



    fun register( email:String, pwd:String, code:String){

        var impl= UserServerImpl();
        impl.register(email,pwd,code)
                .excute(object : BaseRxObserver<BaseResponse<String>>(){
                    override fun success(data: BaseResponse<String>) {

                        mView.onRegisterSuccess(data.data)

                    }

                    override fun failure(statusCode: Int, msg: String?) {
                        mView.onRegisterFailure(statusCode,msg)

                    }



                })



    }


}