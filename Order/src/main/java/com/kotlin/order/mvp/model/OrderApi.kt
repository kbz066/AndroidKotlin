package com.kotlin.order.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.*
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

    /*
    提交订单
    */
    @POST("order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderRequest): Observable<BaseResponse<String>>


    /*
        根据ID获取订单
     */
    @POST("order/getOrderList")
    fun getOrderList(@Body req: GetOrderListRequest):Observable<BaseResponse<MutableList<OrderResponse>?>>




    /*
        取消订单
     */
    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderRequest): Observable<BaseResponse<String>>

    /*
        确认订单
     */
    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderRequest): Observable<BaseResponse<String>>

}