package com.kotlin.base.common

import android.app.Application
import com.kotlin.base.dagger.component.BaseApplicationComponent
import com.kotlin.base.dagger.component.DaggerBaseApplicationComponent
import com.kotlin.base.dagger.module.BaseApplicationModule
import com.vondear.rxtools.RxTool
import javax.inject.Inject


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
        RxTool.init(this)
    }

    private fun injectAppComponent() {
        mAppComponent= DaggerBaseApplicationComponent.builder().baseApplicationModule(BaseApplicationModule(this)).build();
    }


    companion object {
        lateinit var mInstance: BaseApplication;
    }




}