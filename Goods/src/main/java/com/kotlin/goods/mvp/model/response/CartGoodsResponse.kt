package com.kotlin.goods.mvp.model.response

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/6/1.
 */
@Poko
data class CartGoodsResponse (        var id: Int,//购物车单项商品ID
                                      var goodsId:Int,//具体商品ID
                                      var goodsDesc: String,//商品描述
                                      var goodsIcon: String,//商品图片
                                      var goodsPrice: Long,//商品价格
                                      var goodsCount: Int,//商品数量
                                      var goodsSku:String,//商品SKU
                                      var isSelected:Boolean//是否选中 
 )