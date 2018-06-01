package com.kotlin.goods.dagger.module

import com.kotlin.goods.mvp.model.server.CartService
import com.kotlin.goods.mvp.model.server.GoodsListServer
import com.kotlin.goods.mvp.model.server.impl.CartServiceImpl
import com.kotlin.goods.mvp.model.server.impl.GoodsListServerImpl
import com.kotlin.user.mvp.model.server.CategoryServer
import com.kotlin.user.mvp.model.server.impl.CategoryServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/25.
 */
@Module
class CartModule {

    @Provides
    fun providesCartService(impl: CartServiceImpl): CartService {
        return impl
    }
}