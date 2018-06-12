package com.kotlin.order.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.AddShipAddressRequest
import com.kotlin.order.mvp.model.request.DeleteShipAddressRequest
import com.kotlin.order.mvp.model.response.ShipAddress
import io.reactivex.Observable
import retrofit2.http.Body

/**
 * Created by  on 2018/6/11.
 */
interface ReceivingAddressServer {

    fun addShipAddress( shipUserName: String,  shipUserMobile: String,  shipAddress: String): Observable<BaseResponse<String>>

    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddress>?>>
    fun deleteShipAddress(id: Int): Observable<BaseResponse<String>>

    fun setDefaultShipAddress(shipAddress: ShipAddress): Observable<BaseResponse<String>>
    fun editShipAddress(shipAddress: ShipAddress): Observable<BaseResponse<String>>

}