package com.kotlin.pay.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter

import com.kotlin.pay.mvp.model.server.PayServer
import com.kotlin.pay.mvp.presenter.view.IPayView
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * Created by  on 2018/6/8.
 */
class PayPresenter @Inject constructor() : BasePresenter<IPayView>() {



    @Inject
    lateinit var mPayServer: PayServer

    fun getPaySign(orderId: Int,  totalPrice: Long){

        mPayServer.getPaySign(orderId,totalPrice)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView) {
                    override fun success(data: BaseResponse<String>) {

                        mView.onGetSignResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                        Logger.e("failure\t\t\t\t"+msg)
                    }

                },rxLifecycle);
    }




}