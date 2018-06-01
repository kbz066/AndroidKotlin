package com.kotlin.goods.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.mvp.model.request.GetGoodsListRequest
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.model.response.GoodsSkuResponse

/**
 * Created by caofu on 2018/5/25.
 */
interface IGoodsDetailView :BaseView{
    //获取商品详情
    fun onGetGoodsDetailResult(result: GoodsListResponse)

    //加入购物车
    fun onAddCartResult(result: Int)
}