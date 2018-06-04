package com.kotlin.goods.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.utils.EventBusUtils

import com.kotlin.goods.R
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.GoodsDetailImageEvent
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*
import org.greenrobot.eventbus.Subscribe


class GoodsDetailTabTwoFragment : BaseFragment() {
    override fun getContentViewResId(): Int {
        return R.layout.fragment_goods_detail_tab_two
    }

    override fun initView() {

        EventBusUtils.register(this)
    }

    @Subscribe()
    fun onMessageEvent(event: GoodsDetailImageEvent){

        iv_goods_detail_one.loadImageFitCenter(event.imgOne)
        iv_goods_detail_two.loadImageFitCenter(event.imgTwo)
    }

    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }

}
