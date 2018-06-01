package com.kotlin.goods.mvp.model.response

import com.kotlin.base.annotation.Poko

/*
    商品数据类
 */
@Poko
data class GoodsListResponse(
        var id: Int,//商品ID
        var categoryId: Int,//分类ID
        var goodsDesc: String,//商品描述
        var goodsDefaultIcon: String,//默认图标
        var goodsDefaultPrice: Long,//默认价格
        var goodsDetailOne: String,//商品详情一图
        var goodsDetailTwo: String,//商品详情二图
        var goodsSalesCount: Int,//商品销量
        var goodsStockCount: Int,//商品剩余量
        var goodsCode: String,//商品编号
        var goodsDefaultSku: String,//默认SKU
        var goodsBanner: String,//商品banner图
        var goodsSku:List<GoodsSkuResponse>,//商品SKU
        var maxPage:Int//最大页码
        )
