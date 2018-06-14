package com.kotlin.pay.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.pay.mvp.model.PayApi
import com.kotlin.pay.mvp.model.request.GetPaySignRequest
import com.kotlin.pay.mvp.model.request.PayOrderRequest
import com.kotlin.pay.mvp.model.server.PayServer
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by  on 2018/6/14.
 */
class PayServerImpl @Inject constructor():PayServer{
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResponse<String>> {

        return RetrofitManager.mInstance.create(PayApi::class.java)
                .getPaySign(GetPaySignRequest(orderId,totalPrice))
    }

    override fun payOrder(orderId: Int): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(PayApi::class.java)
                .payOrder(PayOrderRequest(orderId))

    }
}