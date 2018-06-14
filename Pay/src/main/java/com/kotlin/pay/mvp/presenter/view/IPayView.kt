package com.kotlin.pay.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Created by  on 2018/6/14.
 */
interface IPayView :BaseView {
    //获取支付签名
    fun onGetSignResult(result: String)
    //同步支付成功状态
    fun onPayOrderResult()
}