package com.kotlin.goods.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.mvp.model.response.GoodsListResponse

/**
 * Created by  on 2018/5/25.
 */
interface IGoodsListView :BaseView{
    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<GoodsListResponse>?)
}