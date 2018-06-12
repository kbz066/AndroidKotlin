package com.kotlin.order.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.order.mvp.model.request.AddShipAddressRequest
import com.kotlin.order.mvp.model.request.DeleteShipAddressRequest
import com.kotlin.order.mvp.model.request.EditShipAddressRequest
import com.kotlin.order.mvp.model.response.ShipAddress
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

    @POST("shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddress>?>>

    /*
    删除收货地址
 */
    @POST("shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressRequest): Observable<BaseResponse<String>>


    /*
        修改收货地址
     */
    @POST("shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressRequest): Observable<BaseResponse<String>>


}