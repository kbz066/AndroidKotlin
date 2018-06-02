package com.kotlin.base.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.dagger.component.BaseApplicationComponent
import com.kotlin.base.dagger.component.DaggerBaseApplicationComponent
import com.kotlin.base.dagger.module.BaseApplicationModule
import com.orhanobut.logger.*
import com.vondear.rxtools.RxTool
import javax.inject.Inject
import com.orhanobut.logger.Logger.addLogAdapter
import com.vondear.rxtools.RxCrashTool


/**
 * Created by  on 2018/5/2.
 */
class BaseApplication  :Application() {



    lateinit var mAppComponent:BaseApplicationComponent;

    override fun onCreate() {
        super.onCreate()
        mInstance=this;

        initLibrary()//初始化开源库
        injectAppComponent()
    }

    private fun initLibrary() {




        //初始化 logger 日志库
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("日志输出")
                .build()

       Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        ARouter.init(this);
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

    }

    private fun injectAppComponent() {
        mAppComponent= DaggerBaseApplicationComponent.builder().baseApplicationModule(BaseApplicationModule(this)).build();
    }


    companion object {
        lateinit var mInstance: BaseApplication;
    }




}