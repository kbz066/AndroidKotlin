package com.kotlin.mall.common

import cn.jpush.android.api.JPushInterface
import com.kotlin.base.common.BaseApplication

/**
 * Created by  on 2018/6/15.
 */
class MainApplication : BaseApplication() {
    override fun onCreate() {

        super.onCreate()
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}