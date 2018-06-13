package com.kotlin.order.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.order.R
import com.kotlin.order.event.UpdateOrderListEvent
import com.kotlin.order.mvp.view.adapter.OrderListPagerAdapter
import kotlinx.android.synthetic.main.activity_order_list.*
import org.greenrobot.eventbus.Logger
import org.greenrobot.eventbus.Subscribe

class OrderListActivity : BaseActivity() {

    private lateinit var mPagerAdapter :OrderListPagerAdapter
    override fun getContentViewResId(): Int {
        return R.layout.activity_order_list

    }

    override fun initView() {
        EventBusUtils.register(this)
        mPagerAdapter=OrderListPagerAdapter(fragmentManager)
        vp_order_pager.adapter=mPagerAdapter
        tab_order.setupWithViewPager(vp_order_pager)

    }



    @Subscribe
    fun onMessage(event:UpdateOrderListEvent){

        com.orhanobut.logger.Logger.e("UpdateOrderListEvent          ")
        mPagerAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }


}
