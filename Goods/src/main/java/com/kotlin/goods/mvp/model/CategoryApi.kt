package com.kotlin.goods.mvp.model

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.goods.mvp.model.request.GetCategoryRequest
import com.kotlin.goods.mvp.model.response.CategoryResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/5/24.
 */
interface CategoryApi {

    /*
    获取商品分类列表
 */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryRequest): Observable<BaseResponse<MutableList<CategoryResponse>?>>
}