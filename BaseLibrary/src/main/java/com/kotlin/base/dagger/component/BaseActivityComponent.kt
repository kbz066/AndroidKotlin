package com.kotlin.base.dagger.component

import android.content.Context
import com.kotlin.base.dagger.module.LifecycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.RxLifecycle
import dagger.Component

/**
 * Created by  on 2018/5/7.
 */

@Component(dependencies = arrayOf(BaseApplicationComponent::class),modules = arrayOf(LifecycleProviderModule::class))
interface BaseActivityComponent {

    fun context():Context

    fun rxLifecycle(): LifecycleProvider<*>
}