package com.kotlin.goods.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.goods.dagger.module.CartModule
import com.kotlin.goods.dagger.module.CategoryModule
import com.kotlin.goods.mvp.view.fragment.CartMainFragment
import com.kotlin.goods.mvp.view.fragment.CategoryFragment

import dagger.Component

/**
 * Created by  on 2018/5/7.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(CartModule::class) )
interface CartComponent {
    fun inject(cartMainFragment: CartMainFragment)

}