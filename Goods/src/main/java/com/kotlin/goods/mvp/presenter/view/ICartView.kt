package com.kotlin.goods.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.mvp.model.response.CartGoodsResponse

/**
 * Created by  on 2018/6/4.
 */
interface ICartView :BaseView{
    fun onGetCartListResult(data: MutableList<CartGoodsResponse>?)
}