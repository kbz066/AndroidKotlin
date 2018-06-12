package com.kotlin.order.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.mvp.model.response.ShipAddress

/**
 * Created by  on 2018/6/11.
 */
interface IEditReceivingAddressView :BaseView{
    //添加收货人回调
    fun onAddShipAddressResult(result: String?)
    fun onEditAddressResulresult(result: String?)

}
