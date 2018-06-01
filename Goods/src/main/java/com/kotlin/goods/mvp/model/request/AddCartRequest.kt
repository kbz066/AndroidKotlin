package com.kotlin.goods.mvp.model.request

/**
 * Created by  on 2018/6/1.
 */
data  class AddCartRequest(
        var goodsId: Int, //商品ID
        var goodsDesc: String, //商品描述
        var goodsIcon: String, //商品图标
        var goodsPrice: Long, //商品价格
        var goodsCount: Int, //商品数量
        var goodsSku: String //商品SKU
)