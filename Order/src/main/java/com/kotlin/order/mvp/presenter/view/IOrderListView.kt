package com.kotlin.order.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.mvp.model.response.OrderResponse

/**
 * Created by  on 2018/6/13.
 */
interface IOrderListView :BaseView{
    fun onGetOrderListResult(result: MutableList<OrderResponse>?)

    fun onCancelOrderResult()

    fun onConfirmOrderResult()
}