package com.kotlin.user.dagger.module

import com.kotlin.user.mvp.model.server.UploadImageServer
import com.kotlin.user.mvp.model.server.impl.UploadImageServerImpl
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/11.
 */

@Module
class UploadImageModule {
    @Provides
    fun providesUploadImageServer(): UploadImageServer {
        return UploadImageServerImpl()
    }
}