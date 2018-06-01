package com.kotlin.goods.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.request.AddCartRequest
import com.kotlin.goods.mvp.model.request.GetCategoryRequest
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import com.kotlin.goods.mvp.model.response.CategoryResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/5/24.
 */
interface CartApi {

    /*
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): rx.Observable<BaseResponse<MutableList<CartGoodsResponse>?>>

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartRequest): Observable<BaseResponse<Int>>

//    /*
//        删除购物车商品
//     */
//    @POST("cart/delete")
//    fun deleteCartList(@Body req: DeleteCartReq): rx.Observable<BaseResp<String>>
//
//    /*
//        提交购物车商品
//     */
//    @POST("cart/submit")
//    fun submitCart(@Body req: SubmitCartReq): rx.Observable<BaseResp<Int>>
}