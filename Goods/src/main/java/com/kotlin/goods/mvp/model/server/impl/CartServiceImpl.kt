package com.kotlin.goods.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.CategoryApi
import com.kotlin.goods.mvp.model.request.AddCartRequest
import com.kotlin.goods.mvp.model.request.DeleteCartRequest
import com.kotlin.goods.mvp.model.request.GetCategoryRequest
import com.kotlin.goods.mvp.model.request.SubmitCartRequest
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import com.kotlin.goods.mvp.model.server.CartApi
import com.kotlin.goods.mvp.model.server.CartService
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by  on 2018/6/1.
 */
class CartServiceImpl @Inject constructor(): CartService {


    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<BaseResponse<Int>> {
        return RetrofitManager.mInstance.create(CartApi::class.java)
                .addCart(AddCartRequest(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }
    override fun getCartList(): Observable<BaseResponse<MutableList<CartGoodsResponse>?>> {
        return RetrofitManager.mInstance.create(CartApi::class.java)
                .getCartList()
    }

    override fun deleteCartList(list: List<Int>): Observable<BaseResponse<String>> {

        return RetrofitManager.mInstance.create(CartApi::class.java)
                .deleteCartList(DeleteCartRequest(list))
    }

    override fun submitCart(list: MutableList<CartGoodsResponse>, totalPrice: Long):Observable<BaseResponse<Int>> {
        return RetrofitManager.mInstance.create(CartApi::class.java)
                .submitCart(SubmitCartRequest(list,totalPrice))
    }


}