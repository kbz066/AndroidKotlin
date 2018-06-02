package com.kotlin.mall.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.event.UserSignoutEvent

import com.kotlin.base.ext.loadUrlImage
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.utils.EventBusUtils

import com.kotlin.mall.R
import com.kotlin.mall.R.id.iv_user_icon
import com.kotlin.mall.R.id.tv_user_name
import com.kotlin.mall.ui.activity.SettingActivity
import com.kotlin.provider.common.isLogin
import com.kotlin.user.mvp.activity.LoginActivity
import com.kotlin.user.mvp.activity.UserInfoActivity
import com.kotlin.user.mvp.model.response.UserInfoResponse
import com.kotlin.user.utils.UserInfoUtils
import com.vondear.rxtools.RxWebViewTool.loadData


import kotlinx.android.synthetic.main.fragment_me.*
import org.greenrobot.eventbus.Logger
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity


class MeFragment : BaseFragment() ,View.OnClickListener{


    override fun getContentViewResId(): Int {
     return R.layout.fragment_me
    }

    override fun initView() {

        EventBusUtils.register(this)

        iv_user_icon.setOnClickListener(this)
        tv_user_name.setOnClickListener(this)
        tv_setting.setOnClickListener(this)
        loadData()
    }

    /**
     * 加载数据
     */
    private fun loadData() {

        if (isLogin()){
            UserInfoUtils.getUserInfo().let {
                if (it.userIcon.isNotEmpty()){
                    iv_user_icon.loadUrlImage(it.userIcon)
                }

                tv_user_name.setText(it.userName)
            }
        }else{
            iv_user_icon.setImageResource(R.drawable.icon_default_user)
            tv_user_name.text = "登录/注册"


        }
    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.iv_user_icon,R.id.tv_user_name->{
                if (isLogin()){
                    startActivity<UserInfoActivity>()
                }else{
                    startActivity<LoginActivity>()

                }
            }
            R.id.tv_setting->{
                startActivity<SettingActivity>()
            }
        }
    }

    /**
     * 保存用户信息
     */
    @Subscribe
    fun onMessageEvent(event: UserInfoResponse) {


        UserInfoUtils.putUserInfo(event)
        loadData()



    }

    /**
     * 退出登录
     */
    @Subscribe
    fun onMessageEvent(event: UserSignoutEvent) {



        UserInfoUtils.removeUserInfo()
        loadData()


    }

    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }


}
