package com.kotlin.goods.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.message.mvp.model.response.MessageResponse
import io.reactivex.Observable


/**
 * Created by  on 2018/6/1.
 */
interface MessageService {

    fun getMessageList(): Observable<BaseResponse<MutableList<MessageResponse>?>>

}