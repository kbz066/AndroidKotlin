package com.kotlin.order.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.CancelOrderRequest
import com.kotlin.order.mvp.model.request.ConfirmOrderRequest
import com.kotlin.order.mvp.model.request.GetOrderByIdRequest
import com.kotlin.order.mvp.model.request.SubmitOrderRequest
import com.kotlin.order.mvp.model.response.OrderResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/6/8.
 */
interface OrderServer {
    fun getOrderById(orderId: Int): Observable<BaseResponse<OrderResponse>>

    fun submitOrder(rder: OrderResponse): Observable<BaseResponse<String>>

    /*
    根据状态查询订单列表
 */
    fun getOrderList(orderStatus: Int): Observable<BaseResponse<MutableList<OrderResponse>?>>



    /*
        取消订单
     */

    fun cancelOrder(orderId:Int): Observable<BaseResponse<String>>

    /*
        确认订单
     */

    fun confirmOrder(orderId:Int): Observable<BaseResponse<String>>
}