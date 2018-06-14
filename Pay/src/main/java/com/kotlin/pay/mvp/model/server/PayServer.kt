package com.kotlin.pay.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.pay.mvp.model.request.GetPaySignRequest
import com.kotlin.pay.mvp.model.request.PayOrderRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/6/14.
 */
interface PayServer {


    fun getPaySign( orderId: Int,  totalPrice: Long): Observable<BaseResponse<String>>

    /*
        刷新订单状态，已支付
     */

    fun payOrder( orderId:Int): Observable<BaseResponse<String>>
}