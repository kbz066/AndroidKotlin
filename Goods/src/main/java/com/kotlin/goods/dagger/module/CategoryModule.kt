package com.kotlin.goods.dagger.module

import com.kotlin.user.mvp.model.server.CategoryServer
import com.kotlin.user.mvp.model.server.impl.CategoryServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by caofu on 2018/5/24.
 */
@Module
class CategoryModule {

    @Provides
    fun providesUserServer(impl: CategoryServerImpl): CategoryServer {
        return impl
    }
}