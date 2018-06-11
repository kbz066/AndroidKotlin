package com.kotlin.order.dagger.module

import com.kotlin.order.mvp.model.server.ReceivingAddressServer
import com.kotlin.order.mvp.model.server.impl.ReceivingAddressServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/6/11.
 */
@Module
class ReceivingAddressModule {
    @Provides
    fun providesOrderServer(impl: ReceivingAddressServerImpl): ReceivingAddressServer {
        return impl
    }
}