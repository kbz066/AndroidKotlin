package com.kotlin.order.common

/*
    订单常量
 */
class OrderConstant {
    companion object {
        //订单状态
        const val KEY_ORDER_STATUS = "order_status"
        //收货地址
        const val KEY_SHIP_ADDRESS = "ship_address"
        //选择收货地址请求码
        const val REQ_SELECT_ADDRESS = 1001
        //是否选择收货地址
        const val KEY_IS_SELECT_ADDRESS = "is_select_address"
        //是否编辑地址
        const val KEY_ADDRESS_IS_EDIT = "address_is_edit"
        //支付订单操作
        const val OPT_ORDER_PAY = 1
        //确认订单操作
        const val OPT_ORDER_CONFIRM = 2
        //取消订单操作
        const val OPT_ORDER_CANCEL = 3


        const val ORDER_ALL = 0//全部
        const val ORDER_WAIT_PAY = 1//待支付
        const val ORDER_WAIT_CONFIRM = 2//待收货
        const val ORDER_COMPLETED = 3//已完成
        const val ORDER_CANCELED = 4//已取消
    }


}
