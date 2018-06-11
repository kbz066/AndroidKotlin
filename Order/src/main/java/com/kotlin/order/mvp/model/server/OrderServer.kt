package com.kotlin.order.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.GetOrderByIdRequest
import com.kotlin.order.mvp.model.response.OrderResponse
import io.reactivex.Observable
import retrofit2.http.Body


/**
 * Created by  on 2018/6/8.
 */
interface OrderServer {
    fun getOrderById(orderId: Int): Observable<BaseResponse<OrderResponse>>
}