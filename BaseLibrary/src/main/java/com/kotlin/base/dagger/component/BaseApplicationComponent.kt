package com.kotlin.base.dagger.component

import android.content.Context
import com.kotlin.base.dagger.module.BaseApplicationModule
import dagger.Component

/**
 * Created by  on 2018/5/7.
 */

@Component(modules = arrayOf(BaseApplicationModule::class))
interface BaseApplicationComponent {

    fun context():Context;
}