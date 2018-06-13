package com.kotlin.order.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.order.dagger.module.OrderModule
import com.kotlin.order.mvp.view.activity.OrderConfirmActivity
import com.kotlin.order.mvp.view.fragment.OrderListFragment
import dagger.Component

/**
 * Created by  on 2018/6/7.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(OrderModule::class) )
interface OrderComponent {
    fun inject(orderConfirmActivity: OrderConfirmActivity)
    fun inject(orderListFragment: OrderListFragment)
}