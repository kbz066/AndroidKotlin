package com.kotlin.goods.mvp.model.request

import com.kotlin.goods.mvp.model.response.CartGoodsResponse

/**
 * Created by  on 2018/6/7.
 */
data class SubmitCartRequest(val goodsList: List<CartGoodsResponse>,val totalPrice: Long)