package com.kotlin.order.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.order.mvp.model.response.OrderResponse
import com.kotlin.order.mvp.model.server.OrderServer
import com.kotlin.order.mvp.presenter.view.IOrderConfirmView
import com.kotlin.order.mvp.presenter.view.IOrderDetailView
import javax.inject.Inject

/**
 * Created by  on 2018/6/8.
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<IOrderDetailView>() {



    @Inject
    lateinit var mOrderServer: OrderServer

    fun getOrderById(orderId: Int){

        mOrderServer.getOrderById(orderId)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<OrderResponse>>(mView) {
                    override fun success(data: BaseResponse<OrderResponse>) {

                        mView.onGetOrderByIdResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }

                },rxLifecycle);
    }




}