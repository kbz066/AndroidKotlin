package com.kotlin.goods.mvp.model.response

import com.kotlin.base.annotation.Poko

/*
    商品SKU数据类
 */
@Poko
data class GoodsSkuResponse(
        var id: Int,
        var skuTitle: String,//SKU标题
        var skuContent: List<String>//SKU内容
        )


