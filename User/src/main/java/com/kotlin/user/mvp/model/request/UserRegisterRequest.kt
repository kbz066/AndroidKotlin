package com.kotlin.user.mvp.model.request

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/5/3.
 */
@Poko
data class UserRegisterRequest constructor(var mobile:String,var pwd:String,var verifyCode:String)