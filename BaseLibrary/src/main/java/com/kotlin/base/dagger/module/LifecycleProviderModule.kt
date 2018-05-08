package com.kotlin.base.dagger.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by  on 2018/5/7.
 */
@Module
class LifecycleProviderModule(private var lifecycleProvider: LifecycleProvider<*>) {


    @Provides
    fun providesLifecycle(): LifecycleProvider<*>{
        return this.lifecycleProvider
    }
}