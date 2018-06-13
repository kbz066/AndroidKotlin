package com.kotlin.order.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.OrderApi
import com.kotlin.order.mvp.model.request.*
import com.kotlin.order.mvp.model.response.OrderResponse
import com.kotlin.order.mvp.model.server.OrderServer
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by  on 2018/6/8.
 */
class OrderServerImpl @Inject constructor() : OrderServer {


    override fun getOrderById(orderId: Int): Observable<BaseResponse<OrderResponse>> {
        return RetrofitManager.mInstance.create(OrderApi::class.java).getOrderById(GetOrderByIdRequest(orderId))
    }

    override fun submitOrder(rder: OrderResponse): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(OrderApi::class.java).submitOrder(SubmitOrderRequest(rder))
    }

    override fun getOrderList(orderStatus: Int): Observable<BaseResponse<MutableList<OrderResponse>?>> {
        return RetrofitManager.mInstance.create(OrderApi::class.java).getOrderList(GetOrderListRequest(orderStatus))
    }


    override fun cancelOrder(orderId: Int): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(OrderApi::class.java).cancelOrder(CancelOrderRequest(orderId))
    }

    override fun confirmOrder(orderId: Int): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(OrderApi::class.java).confirmOrder(ConfirmOrderRequest(orderId))
    }

}