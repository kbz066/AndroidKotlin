package com.kotlin.order.mvp.presenter

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.order.mvp.model.server.ReceivingAddressServer
import com.kotlin.order.mvp.presenter.view.IReceivingAddressView
import javax.inject.Inject

/**
 * Created by  on 2018/6/11.
 */
class ReceivingAddressPresenter @Inject constructor() : BasePresenter<IReceivingAddressView>() {


    @Inject
    lateinit var mReceivingAddressServer: ReceivingAddressServer

    fun addShipAddress(shipUserName: String,  shipUserMobile: String,  shipAddress: String){
        mReceivingAddressServer.addShipAddress(shipUserName,shipUserMobile,shipAddress)
    }
}