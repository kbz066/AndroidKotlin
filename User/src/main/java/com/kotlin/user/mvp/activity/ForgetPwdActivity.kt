package com.kotlin.user.mvp.activity

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hwangjr.rxbus.RxBus
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.R.id.*
import com.kotlin.user.dagger.component.DaggerUserComponent
import com.kotlin.user.dagger.module.UserModule

import com.kotlin.user.mvp.presenter.ForgetPwdPresenter
import com.kotlin.user.mvp.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*


import org.jetbrains.anko.toast


class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(),ForgetPwdView ,View.OnClickListener{



    override fun initView() {

        setSupportActionBar(tb_forgetpwd_bar.getToolBar())

        bt_forget_next.enable(et_mobile_number,et_verify_code){
            isBtnEnable()
        }
        bt_forget_next.setOnClickListener(this)
        vb_verification.setOnClickListener(this)

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
        return R.layout.activity_forget_pwd
    }




    /**
     * 点击事件
     */
    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.vb_verification->{

                toast("发送验证码成功")
                vb_verification.afterMeterTime()
            }
            R.id.bt_forget_next->{

                mPresenter.forgetPwd(et_mobile_number.text.toString(),et_verify_code.text.toString())
            }

        }
    }
    fun isBtnEnable():Boolean{
        return et_mobile_number.text.isNotEmpty()&&
                et_verify_code.text.isNotEmpty()

    }

    /**
     * 回调方法
     */
    override fun onForgetPwdResult(result: String?) {

        toast("验证成功")
        RxBus.get().

    }

    override fun onError(statusCode: Int, msg: String?) {
        super.onError(statusCode, msg)
        toast("验证失败(${msg})")
    }

}
