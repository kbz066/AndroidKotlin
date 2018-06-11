package com.kotlin.order.mvp.model.response

/**
 * Created by  on 2018/6/8.
 */
data class ShipAddress(
        val id: Int,
        var shipUserName: String,
        var shipUserMobile: String,
        var shipAddress: String,
        var shipIsDefault: Int
)