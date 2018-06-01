package com.kotlin.goods.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import io.reactivex.Observable


/**
 * Created by  on 2018/6/1.
 */
interface CartService {
    /*
    添加商品到购物车
 */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResponse<Int>>

//    /*
//        获取购物车列表
//     */
//    fun getCartList(): Observable<MutableList<CartGoodsResponse>?>
//
//    /*
//        删除购物车商品
//     */
//    fun deleteCartList(list: List<Int>): Observable<Boolean>
//
//    /*
//        购物车结算
//    */
//    fun submitCart(list: MutableList<CartGoodsResponse>, totalPrice: Long): Observable<Int>

}