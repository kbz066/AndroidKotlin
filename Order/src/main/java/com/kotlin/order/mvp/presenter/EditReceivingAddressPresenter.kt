package com.kotlin.order.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.order.mvp.model.response.ShipAddress
import com.kotlin.order.mvp.model.server.ReceivingAddressServer
import com.kotlin.order.mvp.presenter.view.IEditReceivingAddressView
import com.kotlin.order.mvp.presenter.view.IReceivingAddressView
import javax.inject.Inject

/**
 * Created by  on 2018/6/11.
 */
class EditReceivingAddressPresenter @Inject constructor() : BasePresenter<IEditReceivingAddressView>() {


    @Inject
    lateinit var mReceivingAddressServer: ReceivingAddressServer

    fun addShipAddress(shipUserName: String,  shipUserMobile: String,  shipAddress: String){
        mReceivingAddressServer.addShipAddress(shipUserName,shipUserMobile,shipAddress)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {

                        mView.onAddShipAddressResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }
    fun editShipAddress(shipAddress: ShipAddress){

        mReceivingAddressServer.editShipAddress(shipAddress)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView){
                    override fun success(data: BaseResponse<String>) {
                        mView.onEditAddressResulresult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }

}