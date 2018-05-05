package com.kotlin.user.mvp.model.request

import com.kotlin.base.annotation.Poko

/**
 * Created by caofu on 2018/5/5.
 */
@Poko
data class UserLoginRequest(val mobile:String, val pwd:String, val pushId:String)