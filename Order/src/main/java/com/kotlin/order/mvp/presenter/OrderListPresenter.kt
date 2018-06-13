package com.kotlin.order.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.order.mvp.model.response.OrderResponse
import com.kotlin.order.mvp.model.server.OrderServer
import com.kotlin.order.mvp.presenter.view.IOrderConfirmView
import com.kotlin.order.mvp.presenter.view.IOrderListView
import javax.inject.Inject

/**
 * Created by  on 2018/6/8.
 */
class OrderListPresenter @Inject constructor() : BasePresenter<IOrderListView>() {



    @Inject
    lateinit var mOrderServer: OrderServer

    fun getOrderList(orderStatus: Int){

        mOrderServer.getOrderList(orderStatus)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<OrderResponse>?>>(mView) {
                    override fun success(data: BaseResponse<MutableList<OrderResponse>?>) {

                        mView.onGetOrderListResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }

                },rxLifecycle);
    }


    fun cancelOrder(orderId:Int){

        mOrderServer.cancelOrder(orderId)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView) {
                    override fun success(data: BaseResponse<String>) {

                        mView.onCancelOrderResult()
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }

                },rxLifecycle);
    }

    fun confirmOrder(orderId:Int){

        mOrderServer.confirmOrder(orderId)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView) {
                    override fun success(data: BaseResponse<String>) {

                        mView.onConfirmOrderResult()
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }

                },rxLifecycle);
    }


}