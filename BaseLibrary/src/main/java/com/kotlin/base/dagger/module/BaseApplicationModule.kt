package com.kotlin.base.dagger.module

import android.content.Context
import com.kotlin.base.common.BaseApplication
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/7.
 */
@Module
class BaseApplicationModule(private var context:BaseApplication) {

    @Provides
     fun providesBaseApplication():Context{
        return this.context
    }
}