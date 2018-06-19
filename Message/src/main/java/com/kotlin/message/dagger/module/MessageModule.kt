package com.kotlin.goods.dagger.module

import com.kotlin.goods.mvp.model.server.MessageService
import com.kotlin.goods.mvp.model.server.impl.MessageServiceImpl

import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/25.
 */
@Module
class MessageModule {

    @Provides
    fun providesMessageService(impl: MessageServiceImpl): MessageService {
        return impl
    }
}