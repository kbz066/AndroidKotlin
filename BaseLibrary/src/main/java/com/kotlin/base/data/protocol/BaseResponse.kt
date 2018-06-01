package com.kotlin.base.data.protocol

import com.kotlin.base.annotation.Poko

/**
 * Created by  on 2018/5/3.
 */
@Poko
data class BaseResponse<T> (var status:Int,var message:String, var data:T)

