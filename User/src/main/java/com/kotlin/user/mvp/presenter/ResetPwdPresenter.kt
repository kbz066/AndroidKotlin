package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.server.UserServer
import com.kotlin.user.mvp.presenter.view.ResetPwdView
import javax.inject.Inject


class ResetPwdPresenter @Inject constructor():BasePresenter<ResetPwdView>() {



    @Inject
    lateinit var mUserServer: UserServer





    fun resetPwd( mobile: String, verifyCode: String){


        mUserServer.resetPwd(mobile,verifyCode)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {
                        mView.onResetPwdResult(data.data)

                    }

                    override fun failure(statusCode: Int, msg: String?) {
                        mView.onError(statusCode,msg)
                    }

                },rxLifecycle)



    }


}