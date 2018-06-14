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
import com.kotlin.mall.R.id.*
import com.kotlin.mall.ui.activity.SettingActivity
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.mvp.view.activity.OrderListActivity
import com.kotlin.order.mvp.view.activity.ReceivingAddressActivity
import com.kotlin.provider.common.afterLogin
import com.kotlin.provider.common.isLogin
import com.kotlin.user.mvp.activity.LoginActivity
import com.kotlin.user.mvp.activity.UserInfoActivity
import com.kotlin.user.mvp.model.response.UserInfoResponse
import com.kotlin.user.utils.UserInfoUtils



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
        tv_address.setOnClickListener(this)
        tv_all_order.setOnClickListener(this)
        tv_wait_confirm_order.setOnClickListener(this)
        tv_wait_pay_order.setOnClickListener(this)
        tv_complete_order.setOnClickListener(this)

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
            R.id.tv_address->{
                startActivity<ReceivingAddressActivity>()
            }
            R.id.tv_all_order->{
                afterLogin {
                    startActivity<OrderListActivity>()
                }

            }
            R.id.tv_wait_confirm_order->{
                afterLogin {
                    startActivity<OrderListActivity>(OrderConstant.KEY_ORDER_STATUS to OrderConstant.ORDER_WAIT_CONFIRM)

                }
            }
            R.id.tv_complete_order->{
                afterLogin {
                    startActivity<OrderListActivity>(OrderConstant.KEY_ORDER_STATUS to OrderConstant.ORDER_COMPLETED)

                }
            }
            R.id.tv_wait_pay_order->{

                afterLogin{
                    startActivity<OrderListActivity>(OrderConstant.KEY_ORDER_STATUS to OrderConstant.ORDER_WAIT_PAY)
                }

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
