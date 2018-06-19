package com.kotlin.goods.dagger.component

import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.goods.dagger.module.MessageModule
import com.kotlin.message.mvp.view.fragment.MessageFragment


import dagger.Component

/**
 * Created by  on 2018/5/7.
 */
@Component(dependencies = arrayOf(BaseActivityComponent::class),modules = arrayOf(MessageModule::class) )
interface MessageComponent {
    fun inject(messageFragment: MessageFragment)

}