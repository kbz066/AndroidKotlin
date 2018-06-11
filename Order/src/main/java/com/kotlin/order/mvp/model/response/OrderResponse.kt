package com.kotlin.order.mvp.model.response

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/6/8.
 */
@Poko
data class OrderResponse(
        var id: Int,
        var payType: Int,
        var shipAddress: ShipAddress?,
        var totalPrice: Long,
        var orderStatus: Int,
        var orderGoodsList: MutableList<OrderGoods>
)