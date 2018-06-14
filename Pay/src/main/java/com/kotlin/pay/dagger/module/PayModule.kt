package com.kotlin.pay.dagger.module

import com.kotlin.pay.mvp.model.server.PayServer
import com.kotlin.pay.mvp.model.server.impl.PayServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/6/14.
 */
@Module
class PayModule {
    @Provides
    fun providesPayServer(impl: PayServerImpl): PayServer {
        return impl
    }

}