package com.kotlin.goods.mvp.model.server.impl

import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse

import com.kotlin.goods.mvp.model.server.MessageApi
import com.kotlin.goods.mvp.model.server.MessageService
import com.kotlin.message.mvp.model.response.MessageResponse
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by  on 2018/6/1.
 */
class MessageServiceImpl @Inject constructor(): MessageService {
    override fun getMessageList(): Observable<BaseResponse<MutableList<MessageResponse>?>> {
        return RetrofitManager.mInstance.create(MessageApi::class.java)
                .getMessageList()
    }


}