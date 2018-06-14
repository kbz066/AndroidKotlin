package com.kotlin.pay.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.pay.dagger.module.PayModule
import com.kotlin.pay.mvp.view.activity.PayActivity
import dagger.Component

/**
 * Created by  on 2018/6/14.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(PayModule::class) )
interface PayComponent {
    fun inject(payActivity: PayActivity)
}