package com.kotlin.user.mvp.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.mvp.model.response.UserInfoResponse

/**
 * Created by  on 2018/5/11.
 */
interface UserInfoView :BaseView {
    /*
    获取上传凭证回调
 */
    fun onUploadImageResult(flag:Boolean)

    /*
        编辑用户资料回调
     */
    fun onEditUserResult(result:UserInfoResponse)
}