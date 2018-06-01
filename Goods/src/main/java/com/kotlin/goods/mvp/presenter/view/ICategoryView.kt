package com.kotlin.goods.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.mvp.model.response.CategoryResponse

/**
 * Created by  on 2018/5/24.
 */
interface ICategoryView :BaseView {

    //获取商品分类列表
    fun onGetCategoryResult(result: MutableList<CategoryResponse>?)


}