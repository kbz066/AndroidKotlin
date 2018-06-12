package com.kotlin.order.mvp.model.request

/**
 * Created by  on 2018/6/11.
 */
data class EditShipAddressRequest(val id:Int,val shipUserName:String,val shipUserMobile:String,val shipAddress:String,val shipIsDefault:Int)