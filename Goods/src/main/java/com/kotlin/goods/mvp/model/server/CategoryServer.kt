package com.kotlin.user.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.response.CategoryResponse
import io.reactivex.Observable


/**
 * Created by  on 2018/5/4.
 */
interface CategoryServer {

    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<CategoryResponse>?>>



}