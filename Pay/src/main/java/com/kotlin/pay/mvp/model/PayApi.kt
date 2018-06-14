package com.kotlin.pay.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.pay.mvp.model.request.GetPaySignRequest
import com.kotlin.pay.mvp.model.request.PayOrderRequest
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

/**
 * Created by  on 2018/6/14.
 */
interface PayApi {

    /*
    获取支付宝支付签名
 */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignRequest): Observable<BaseResponse<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderRequest): Observable<BaseResponse<String>>

}