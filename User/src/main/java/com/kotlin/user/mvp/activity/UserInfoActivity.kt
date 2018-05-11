package com.kotlin.user.mvp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.mvp.presenter.UserInfoPresenter
import com.kotlin.user.mvp.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>() ,UserInfoView{
    override fun getContentViewResId(): Int {
        return R.layout.activity_user_info
    }

    override fun initView() {

        setSupportActionBar(tb_user_info_bar.getToolBar())
    }

    override fun injectComponent() {

    }


}
