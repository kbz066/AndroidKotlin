package com.kotlin.goods.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView

import com.kotlin.message.mvp.model.response.MessageResponse

/**
 * Created by  on 2018/6/4.
 */
interface IMessageView :BaseView{

    //获取消息列表回调
    fun onGetMessageResult(result:MutableList<MessageResponse>?)
}