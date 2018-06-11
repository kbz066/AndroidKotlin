package com.kotlin.order.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.GetOrderByIdRequest
import com.kotlin.order.mvp.model.response.OrderResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST



/**
 * Created by  on 2018/6/8.
 */
interface OrderApi {
    /*
    根据ID获取订单
 */
    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdRequest): Observable<BaseResponse<OrderResponse>>
}