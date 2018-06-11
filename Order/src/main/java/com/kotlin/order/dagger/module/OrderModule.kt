package com.kotlin.order.dagger.module

import com.kotlin.order.mvp.model.server.OrderServer
import com.kotlin.order.mvp.model.server.impl.OrderServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/6/7.
 */
@Module
class OrderModule {
    @Provides
    fun providesOrderServer(impl: OrderServerImpl): OrderServer {
        return impl
    }
}