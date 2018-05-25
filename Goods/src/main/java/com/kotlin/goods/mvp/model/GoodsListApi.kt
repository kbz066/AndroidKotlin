package com.kotlin.goods.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.request.GetGoodsDetailRequest
import com.kotlin.goods.mvp.model.request.GetGoodsListByKeywordRequest
import com.kotlin.goods.mvp.model.request.GetGoodsListRequest
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/5/25.
 */
interface GoodsListApi {

    /*
    获取商品列表
 */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListRequest): Observable<BaseResponse<MutableList<GoodsListResponse>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordRequest): Observable<BaseResponse<MutableList<GoodsListResponse>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailRequest): Observable<BaseResponse<GoodsListResponse>>
}