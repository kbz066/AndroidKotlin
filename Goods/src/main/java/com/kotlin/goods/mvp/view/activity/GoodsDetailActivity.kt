package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.goods.R
import com.kotlin.goods.mvp.view.adapter.GoodsDetailPagerAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

class GoodsDetailActivity : BaseActivity() {


    override fun getContentViewResId(): Int {
        return R.layout.activity_goods_detail
    }

    override fun initView() {
        vp_goods_detail_pager.adapter= GoodsDetailPagerAdapter(fragmentManager)
        tl_goods_detail_Tab.tabMode=TabLayout.MODE_FIXED
        tl_goods_detail_Tab.setupWithViewPager(vp_goods_detail_pager)
    }


}
