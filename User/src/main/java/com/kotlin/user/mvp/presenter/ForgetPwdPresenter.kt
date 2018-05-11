package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.server.UserServer
import com.kotlin.user.mvp.presenter.view.ForgetPwdView
import javax.inject.Inject


class ForgetPwdPresenter @Inject constructor():BasePresenter<ForgetPwdView>() {



    @Inject
    lateinit var mUserServer: UserServer





    fun forgetPwd( mobile: String, verifyCode: String){


        mUserServer.forgetPwd(mobile,verifyCode)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {
                        mView.onForgetPwdResult(data.data)

                    }

                    override fun failure(statusCode: Int, msg: String?) {
                        mView.onError(statusCode,msg)
                    }



                },rxLifecycle)



    }


}