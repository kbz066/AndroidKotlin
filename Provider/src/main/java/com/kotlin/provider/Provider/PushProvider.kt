package com.kotlin.provider.Provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by  on 2018/6/15.
 */
interface PushProvider :IProvider{
    fun getPushId():String?
}