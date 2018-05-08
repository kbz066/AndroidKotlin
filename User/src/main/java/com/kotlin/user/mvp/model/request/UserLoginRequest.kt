package com.kotlin.user.mvp.model.request

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/5/5.
 */

data class UserLoginRequest(val mobile:String, val pwd:String, val pushId:String)