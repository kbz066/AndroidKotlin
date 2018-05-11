package com.kotlin.user.mvp.activity

import android.graphics.Canvas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.base.event.ResetPwdMobileEvent
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.user.R
import com.kotlin.user.R.id.*
import com.kotlin.user.dagger.component.DaggerUserComponent
import com.kotlin.user.dagger.module.UserModule
import com.kotlin.user.mvp.presenter.ResetPwdPresenter
import com.kotlin.user.mvp.presenter.view.ResetPwdView
import com.orhanobut.logger.Logger

import kotlinx.android.synthetic.main.activity_reset_pwd.*
import kotlinx.android.synthetic.main.activity_user_info.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>() ,ResetPwdView{



    private var mobile:String?=null
    override fun getContentViewResId(): Int {

        return R.layout.activity_reset_pwd
    }

    override fun injectComponent() {

        DaggerUserComponent.builder().userModule(UserModule())
                .baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    /**
     * 粘性事件
     */

    @Subscribe(sticky = true)
    fun OnMessage(resetPwdMobileEvent: ResetPwdMobileEvent){

        mobile=resetPwdMobileEvent.mobile

        Logger.e("手机号码-----》"+resetPwdMobileEvent.mobile)

    }

    /**
     * 初始化
     */
    override fun initView() {

        EventBusUtils.register(this)

        setSupportActionBar(tb_resetrwd_bar.getToolBar())
        bt_reset_next.enable(et_reset_pwd,et_reset_confirm_pwd){
            isBtnEnable()
        }
        bt_reset_next.setOnClickListener {
            if (et_reset_pwd.text.toString()!=et_reset_confirm_pwd.text.toString()){
                toast("密码不一致")
                return@setOnClickListener

            }
            mPresenter.resetPwd(mobile!!,et_reset_pwd.text.toString())

        }
    }

    fun isBtnEnable():Boolean{
        return et_reset_pwd.text.isNotEmpty()&&
                et_reset_confirm_pwd.text.isNotEmpty()

    }


    /**
     * 回调
     */
    override fun onResetPwdResult(result: String?) {

        toast("密码修改成功")

        startActivity(intentFor<LoginActivity>().clearTop())
    }

    override fun onError(statusCode: Int, msg: String?) {
        super.onError(statusCode, msg)
        toast("密码修改失败(${msg})")
    }
    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }

}
