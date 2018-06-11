package com.kotlin.order.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.ReceivingAddressApi
import com.kotlin.order.mvp.model.request.AddShipAddressRequest
import com.kotlin.order.mvp.model.server.ReceivingAddressServer
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/6/11.
 */
class ReceivingAddressServerImpl @Inject constructor(): ReceivingAddressServer {

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResponse<String>> {

        return RetrofitManager.mInstance.create(ReceivingAddressApi::class.java).addShipAddress(AddShipAddressRequest(shipUserName,shipUserMobile,shipAddress))
    }

}