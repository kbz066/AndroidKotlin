package com.kotlin.user.mvp.model.response

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/5/8.
 */
@Poko
data class UserInfoResponse(var id:String,
                            var userIcon:String?,
                            var userName:String,
                            var userGender:String?,
                            var userMobile:String,
                            var userSign:String?)