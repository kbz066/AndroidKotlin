package com.kotlin.order.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.OrderApi
import com.kotlin.order.mvp.model.request.GetOrderByIdRequest
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
}