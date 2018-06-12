package com.kotlin.order.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.mvp.model.response.ShipAddress

/**
 * Created by  on 2018/6/11.
 */
interface IReceivingAddressView :BaseView{
    fun onGetShipAddressResult(result: MutableList<ShipAddress>?)

    //删除收货人回调
    fun onDeleteResult(result: String?)

    //设置默认收货人回调
    fun onSetDefaultResult(result: String?)


}
