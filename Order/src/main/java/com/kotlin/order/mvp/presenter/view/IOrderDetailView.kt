package com.kotlin.order.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.mvp.model.response.OrderResponse

/**
 * Created by  on 2018/6/8.
 */
interface IOrderDetailView :BaseView{
    //获取订单回调
    fun onGetOrderByIdResult(result:OrderResponse)


}