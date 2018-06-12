package com.kotlin.order.mvp.model.server.impl

import com.alibaba.sdk.android.oss.model.DeleteBucketRequest
import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.ReceivingAddressApi
import com.kotlin.order.mvp.model.request.AddShipAddressRequest
import com.kotlin.order.mvp.model.request.DeleteShipAddressRequest
import com.kotlin.order.mvp.model.request.EditShipAddressRequest
import com.kotlin.order.mvp.model.response.ShipAddress
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

    override fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddress>?>> {
        return RetrofitManager.mInstance.create(ReceivingAddressApi::class.java).getShipAddressList()
    }

    override fun deleteShipAddress(id: Int): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(ReceivingAddressApi::class.java).deleteShipAddress(DeleteShipAddressRequest(id))
    }
    override fun setDefaultShipAddress(shipAddress: ShipAddress): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(ReceivingAddressApi::class.java).
                editShipAddress(EditShipAddressRequest(shipAddress.id,shipAddress.shipUserName,shipAddress.shipUserMobile,shipAddress.shipAddress,shipAddress.shipIsDefault))
    }


    override fun editShipAddress(shipAddress: ShipAddress): Observable<BaseResponse<String>> {
        return RetrofitManager.mInstance.create(ReceivingAddressApi::class.java).
                editShipAddress(EditShipAddressRequest(shipAddress.id,shipAddress.shipUserName,shipAddress.shipUserMobile,shipAddress.shipAddress,shipAddress.shipIsDefault))
    }
}