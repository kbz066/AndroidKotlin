package com.kotlin.user.utils

import com.alibaba.fastjson.JSON
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.common.BaseConstant
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.mvp.model.response.UserInfoResponse
import com.vondear.rxtools.RxSPTool


/**
 * Created by  on 2018/5/21.
 */
object UserInfoUtils {

    fun putUserInfo(userInfo: UserInfoResponse?) {
        userInfo?.let {
            RxSPTool.putString(BaseApplication.mInstance,BaseConstant.KEY_SP_TOKEN, userInfo?.id)
            RxSPTool.putJSONCache(BaseApplication.mInstance, ProviderConstant.KEY_SP_USER_CACHE, JSON.toJSONString(userInfo))
        }

    }
    fun getUserInfo():UserInfoResponse{
        var json=RxSPTool.readJSONCache(BaseApplication.mInstance,ProviderConstant.KEY_SP_USER_CACHE)


        return JSON.parseObject(json,UserInfoResponse::class.java)
    }

    fun removeUserInfo(){
        RxSPTool.remove(BaseApplication.mInstance,BaseConstant.KEY_SP_TOKEN)
        RxSPTool.clearPreference(BaseApplication.mInstance,BaseConstant.SP_JSON_NAME,ProviderConstant.KEY_SP_USER_CACHE)
    }

    fun isLogin():Boolean{
        return RxSPTool.readJSONCache(BaseApplication.mInstance,ProviderConstant.KEY_SP_USER_CACHE)!=null
    }


}