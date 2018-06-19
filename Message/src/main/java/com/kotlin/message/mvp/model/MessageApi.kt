package com.kotlin.goods.mvp.model.server

import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.message.mvp.model.response.MessageResponse

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by  on 2018/5/24.
 */
interface MessageApi {
    /*
        获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResponse<MutableList<MessageResponse>?>>
}