package com.kotlin.user.dagger.module

import com.kotlin.user.mvp.model.server.UserServer
import com.kotlin.user.mvp.model.server.impl.UserServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/7.
 */
@Module
class UserModule {

    @Provides
    fun providesUserServer(): UserServer {
        return UserServerImpl()
    }

}