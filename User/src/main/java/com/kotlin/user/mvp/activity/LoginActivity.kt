package com.kotlin.user.mvp.activity

import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.dagger.component.DaggerUserComponent
import com.kotlin.user.dagger.module.UserModule
import com.kotlin.user.mvp.model.response.UserLoginResponse


import com.kotlin.user.mvp.presenter.LoginPresenter
import com.kotlin.user.mvp.presenter.view.LoginView
import junit.framework.Test


import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>() ,View.OnClickListener,LoginView{


    override fun initView() {
        fb_toRigisterView.setOnClickListener(this)
        bt_userLogin.setOnClickListener(this)

    }

    /**
     * 注入
     */
    override fun injectComponent() {

        DaggerUserComponent.builder().userModule(UserModule())
                .baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mpresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_login;
    }
    override fun onLoginSuccess(result: UserLoginResponse?) {
        toast("登录成功${result?.id}")

        println("登录成功 \t\t\t\t"+result)
    }

    override fun onLoginFailure(statusCode: Int, msg: String?) {
        toast("登录失败(${msg})")
    }


    /**
     * 点击事件
     */
    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.fb_toRigisterView->{


                val options = ActivityOptions.makeSceneTransitionAnimation(this, fb_toRigisterView, fb_toRigisterView.getTransitionName())

                startActivity(Intent(this, RegisterActivity::class.java), options.toBundle())
            }
            R.id.bt_userLogin->{
                mpresenter.login(et_login_phone.text.toString(),et_login_password.text.toString(),"")//登录
            }
        }
    }

    override fun onDestroy() {
        println("LoginActivity-----------onDestroy->")
        super.onDestroy()
    }

}
