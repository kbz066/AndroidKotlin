package com.kotlin.message.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.provider.Provider.PushProvider
import com.kotlin.provider.common.ARouterPath

/**
 * Created by  on 2018/6/15.
 */
@Route(path = ARouterPath.PATH_MESSAGE_PUSH)
class PushProviderImpl:PushProvider {

    lateinit var context: Context
    override fun init(context: Context) {
        this.context=context
    }

    override fun getPushId(): String? {
        return JPushInterface.getRegistrationID(context)
    }
}