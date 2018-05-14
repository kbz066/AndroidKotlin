package com.kotlin.user.mvp.activity

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.os.Environment
import android.view.View
import com.alibaba.fastjson.JSON
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.R


import com.kotlin.user.dagger.component.DaggerUserComponent
import com.kotlin.user.dagger.module.UserModule
import com.kotlin.user.mvp.model.response.UserInfoResponse


import com.kotlin.user.mvp.presenter.LoginPresenter
import com.kotlin.user.mvp.presenter.view.LoginView
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxFileTool
import com.vondear.rxtools.RxSPTool
import kotlinx.android.synthetic.main.activity_login.*


import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>() ,View.OnClickListener,LoginView{


    override fun initView() {


        Logger.e("getDataPath                 "+Environment.getExternalStorageDirectory() )


        setSupportActionBar(tb_login_bar.getToolBar())


        fb_toRigisterView.setOnClickListener(this)
        bt_userLogin.setOnClickListener(this)
        tv_forget_pwd.setOnClickListener(this)

        bt_userLogin.enable(et_login_phone,et_login_password){

            isBtnEnable()
        }

    }


    /**
     * 注入
     */
    override fun injectComponent() {

        DaggerUserComponent.builder().userModule(UserModule())
                .baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_login;
    }

    /**
     * 请求成功的回调
     */
    override fun onLoginSuccess(result: UserInfoResponse?) {
        toast("登录成功${result?.id}")


        RxSPTool.putJSONCache(this,ProviderConstant.KEY_SP_USER_CACHE,JSON.toJSONString(result))

        startActivity<UserInfoActivity>()
    }

    override fun onError(statusCode: Int, msg: String?) {
        super.onError(statusCode, msg)
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
                mPresenter.login(et_login_phone.text.toString(),et_login_password.text.toString(),"")//登录
            }

            R.id.tv_forget_pwd->{


                startActivity<ForgetPwdActivity>()

            }
        }
    }

    fun isBtnEnable():Boolean{
        return et_login_phone.text.isNotEmpty()&&
                et_login_password.text.isNotEmpty()

    }
    override fun onDestroy() {
        println("LoginActivity-----------onDestroy->")
        super.onDestroy()
    }

}
