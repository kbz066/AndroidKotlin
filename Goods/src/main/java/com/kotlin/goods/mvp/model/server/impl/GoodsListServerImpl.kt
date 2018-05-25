package com.kotlin.goods.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.GoodsListApi
import com.kotlin.goods.mvp.model.request.GetGoodsDetailRequest
import com.kotlin.goods.mvp.model.request.GetGoodsListByKeywordRequest
import com.kotlin.goods.mvp.model.request.GetGoodsListRequest
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.model.server.GoodsListServer
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by  on 2018/5/25.
 */
class GoodsListServerImpl @Inject constructor():GoodsListServer {

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResponse<MutableList<GoodsListResponse>?>> {
        return RetrofitManager.mInstance.create(GoodsListApi::class.java)
                .getGoodsList(GetGoodsListRequest(categoryId,pageNo))

    }

    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResponse<MutableList<GoodsListResponse>?>> {
        return RetrofitManager.mInstance.create(GoodsListApi::class.java)
                .getGoodsListByKeyword(GetGoodsListByKeywordRequest(keyword,pageNo))
    }

    override fun getGoodsDetail(goodsId: Int): Observable<BaseResponse<GoodsListResponse>> {
        return RetrofitManager.mInstance.create(GoodsListApi::class.java)
                .getGoodsDetail(GetGoodsDetailRequest(goodsId))
    }

}