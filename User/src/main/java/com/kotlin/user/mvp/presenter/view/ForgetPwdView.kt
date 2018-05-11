package com.kotlin.user.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView

/**
 * Created by  on 2018/5/10.
 */
interface ForgetPwdView :BaseView {

    /*
    忘记密码回调
 */
    fun onForgetPwdResult(result:String?)
}