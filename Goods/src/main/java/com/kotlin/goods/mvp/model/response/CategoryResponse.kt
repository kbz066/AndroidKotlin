package com.kotlin.goods.mvp.model.response

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/5/24.
 */
@Poko
data class CategoryResponse(
        var id: Int, //分类ID
        var categoryName: String, //分类名称
        var categoryIcon: String = "", //分类图标
        var parentId: Int, //分类 父级ID
        var isSelected: Boolean = false//是否被选中
)