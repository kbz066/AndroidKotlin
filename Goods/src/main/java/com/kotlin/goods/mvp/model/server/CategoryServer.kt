package com.kotlin.user.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.request.GetCategoryRequest
import com.kotlin.goods.mvp.model.response.CategoryResPonse
import io.reactivex.Observable

import retrofit2.http.Body


/**
 * Created by  on 2018/5/4.
 */
interface CategoryServer {

    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<CategoryResPonse>?>>



}