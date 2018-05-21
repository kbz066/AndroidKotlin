package com.kotlin.mall.ui.activity


import android.view.View
import com.kotlin.base.event.UserSignoutEvent
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.utils.EventBusUtils

import com.kotlin.mall.R
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : BaseActivity() {
    override fun getContentViewResId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {

        bt_logout.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                EventBusUtils.post(UserSignoutEvent())
                this@SettingActivity.finish()
            }

        })
    }



}
