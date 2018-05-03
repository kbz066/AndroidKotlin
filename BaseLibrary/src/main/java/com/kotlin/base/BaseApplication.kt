package com.kotlin.base

import android.app.Application
import com.kotlin.base.data.net.RetrofitManager



/**
 * Created by  on 2018/5/2.
 */
class BaseApplication  :Application() {


    lateinit var mInstance:BaseApplication;

    override fun onCreate() {
        super.onCreate()
        mInstance=this;
    }






}