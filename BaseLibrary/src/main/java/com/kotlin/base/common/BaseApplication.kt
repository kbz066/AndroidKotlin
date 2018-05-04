package com.kotlin.base.common

import android.app.Application


/**
 * Created by  on 2018/5/2.
 */
class BaseApplication  :Application() {


    lateinit var mInstance: BaseApplication;

    override fun onCreate() {
        super.onCreate()
        mInstance=this;
    }






}