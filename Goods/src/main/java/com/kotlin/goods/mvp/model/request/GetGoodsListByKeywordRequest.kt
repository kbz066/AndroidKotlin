package com.kotlin.goods.mvp.model.request

/**
 * Created by  on 2018/5/25.
 */
data class GetGoodsListByKeywordRequest (        var keyword: String,
                                                 var pageNo: Int)