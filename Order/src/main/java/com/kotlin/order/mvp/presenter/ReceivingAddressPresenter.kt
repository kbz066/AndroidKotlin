package com.kotlin.order.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.order.mvp.model.response.ShipAddress
import com.kotlin.order.mvp.model.server.ReceivingAddressServer
import com.kotlin.order.mvp.presenter.view.IReceivingAddressView
import javax.inject.Inject

/**
 * Created by  on 2018/6/11.
 */
class ReceivingAddressPresenter @Inject constructor() : BasePresenter<IReceivingAddressView>() {


    @Inject
    lateinit var mReceivingAddressServer: ReceivingAddressServer


    //获取列表
    fun getShipAddressList(){

        mReceivingAddressServer.getShipAddressList()
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<ShipAddress>?>>(mView){
                    override fun success(data: BaseResponse<MutableList<ShipAddress>?>) {
                        mView.onGetShipAddressResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }

    fun deleteShipAddress(id: Int){

        mReceivingAddressServer.deleteShipAddress(id)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {
                        mView.onDeleteResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }


    fun setDefaultShipAddress(shipAddress: ShipAddress){

        mReceivingAddressServer.setDefaultShipAddress(shipAddress)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {
                        mView.onSetDefaultResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }



}