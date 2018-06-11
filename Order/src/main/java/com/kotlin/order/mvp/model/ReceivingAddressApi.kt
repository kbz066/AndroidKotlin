package com.kotlin.order.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.AddShipAddressRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/6/11.
 */
interface ReceivingAddressApi {
    /*
    添加收货地址
 */
    @POST("shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressRequest): Observable<BaseResponse<String>>
}