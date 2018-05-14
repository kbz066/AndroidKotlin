package com.kotlin.user.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.mvp.model.response.UserInfoResponse

/**
 * Created by  on 2018/5/5.
 */
interface LoginView: BaseView {

    fun onLoginSuccess(result: UserInfoResponse?)

}