package com.kotlin.goods.mvp.model.response

import com.kotlin.base.annotation.Poko

/*
    商品SKU数据类
 */
@Poko
data class GoodsSku(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
        )


