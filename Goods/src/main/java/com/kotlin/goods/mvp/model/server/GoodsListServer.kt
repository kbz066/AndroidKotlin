package com.kotlin.goods.mvp.model.server

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
interface GoodsListServer {

    /*
    获取商品列表
 */

    fun getGoodsList( categoryId: Int, pageNo: Int): Observable<BaseResponse<MutableList<GoodsListResponse>?>>

    /*
        获取商品列表
     */

    fun getGoodsListByKeyword( keyword: String, pageNo: Int): Observable<BaseResponse<MutableList<GoodsListResponse>?>>

    /*
        获取商品详情
     */

    fun getGoodsDetail(goodsId: Int): Observable<BaseResponse<GoodsListResponse>>
}