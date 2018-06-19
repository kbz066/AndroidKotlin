package com.kotlin.goods.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter

import com.kotlin.goods.mvp.model.server.MessageService
import com.kotlin.goods.mvp.presenter.view.IMessageView
import com.kotlin.message.mvp.model.response.MessageResponse
import javax.inject.Inject

/**
 * Created by  on 2018/6/4.
 */
class MessagePresenter @Inject constructor() :  BasePresenter<IMessageView>() {

    @Inject
    lateinit var mMessageService: MessageService


    fun getMessageList(){
        mMessageService.getMessageList().excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<MessageResponse>?>>(mView) {
            override fun success(data: BaseResponse<MutableList<MessageResponse>?>) {
                mView.onGetMessageResult(data.data)
            }

            override fun failure(statusCode: Int, msg: String?) {

            }


        },rxLifecycle)
    }

}