package com.kotlin.user.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView

interface RigisterView:BaseView {

    fun onRegisterSuccess(result:String)
    fun onRegisterFailure(statusCode: Int, msg: String?)

}