package com.kotlin.order.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.AddShipAddressRequest
import io.reactivex.Observable
import retrofit2.http.Body

/**
 * Created by  on 2018/6/11.
 */
interface ReceivingAddressServer {

    fun addShipAddress( shipUserName: String,  shipUserMobile: String,  shipAddress: String): Observable<BaseResponse<String>>
}