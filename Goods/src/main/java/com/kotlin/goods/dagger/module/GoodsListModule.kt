package com.kotlin.goods.dagger.module

import com.kotlin.goods.mvp.model.server.GoodsListServer
import com.kotlin.goods.mvp.model.server.impl.GoodsListServerImpl
import com.kotlin.user.mvp.model.server.CategoryServer
import com.kotlin.user.mvp.model.server.impl.CategoryServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/25.
 */
@Module
class GoodsListModule {

    @Provides
    fun providesGoodsListServer(impl: GoodsListServerImpl): GoodsListServer {
        return impl
    }
}