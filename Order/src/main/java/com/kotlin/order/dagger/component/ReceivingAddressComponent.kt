package com.kotlin.order.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.order.dagger.module.OrderModule
import com.kotlin.order.dagger.module.ReceivingAddressModule
import com.kotlin.order.mvp.view.activity.ReceivingAddressActivity
import com.kotlin.order.mvp.view.activity.ReceivingAddressEditActivity
import dagger.Component

/**
 * Created by  on 2018/6/11.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(ReceivingAddressModule::class) )
interface ReceivingAddressComponent {
    fun inject(receivingAddressActivity: ReceivingAddressActivity)
    fun inject(receivingAddressEditActivity: ReceivingAddressEditActivity)

}