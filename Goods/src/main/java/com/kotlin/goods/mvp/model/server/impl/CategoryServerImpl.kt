package com.kotlin.user.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.CategoryApi
import com.kotlin.goods.mvp.model.request.GetCategoryRequest
import com.kotlin.goods.mvp.model.response.CategoryResPonse

import com.kotlin.user.mvp.model.server.CategoryServer
import io.reactivex.Observable

import javax.inject.Inject


/**
 * Created by  on 2018/5/4.
 */
class CategoryServerImpl @Inject constructor(): CategoryServer {


    override fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<CategoryResPonse>?>> {
        return RetrofitManager.mInstance.create(CategoryApi::class.java)
                .getCategory(GetCategoryRequest(parentId))
    }


}