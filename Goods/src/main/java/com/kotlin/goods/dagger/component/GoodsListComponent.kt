package com.kotlin.goods.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.goods.dagger.module.CartModule
import com.kotlin.goods.dagger.module.CategoryModule
import com.kotlin.goods.dagger.module.GoodsListModule
import com.kotlin.goods.mvp.view.activity.GoodsListActivity
import com.kotlin.goods.mvp.view.fragment.CategoryFragment
import com.kotlin.goods.mvp.view.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Created by caofu on 2018/5/25.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(GoodsListModule::class,CartModule::class) )
interface GoodsListComponent {
    fun inject(goodsListActivity: GoodsListActivity)
    fun inject(goodsDetailTabOneFragment: GoodsDetailTabOneFragment) {

    }
}