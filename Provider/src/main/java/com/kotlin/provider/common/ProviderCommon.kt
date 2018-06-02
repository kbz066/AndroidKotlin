package com.kotlin.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.common.BaseApplication
import com.vondear.rxtools.RxSPTool

/**
 * Created by  on 2018/5/21.
 */


fun isLogin():Boolean{
    return RxSPTool.readJSONCache(BaseApplication.mInstance,ProviderConstant.KEY_SP_USER_CACHE)!=null
}

fun afterLogin(method:()->Unit){

    if (isLogin()){
        method.invoke()
    }else{
        ARouter.getInstance().build(ARouterPath.PATH_LOGIN).navigation();
    }
}