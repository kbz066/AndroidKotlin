package com.kotlin.mall.ui.fragment


import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ToxicBakery.viewpager.transforms.*
import com.kotlin.base.ext.loadImageRoundedCorners
import com.kotlin.base.ui.fragment.BaseFragment

import com.kotlin.mall.R
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer.DepthPage
import com.kotlin.base.ui.widgets.GlideImageLoader

import com.kotlin.mall.adapter.HomeDiscountAdapter
import com.kotlin.mall.adapter.TopicAdapter
import com.kotlin.mall.common.*
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import me.crosswall.lib.coverflow.core.CoverTransformer


class HomeFragment : BaseFragment() {

    override fun getContentViewResId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initBanner()
        initNews();
        initDiscount()
        initTopic()
    }




    private fun initBanner() {

        //设置图片加载器
        br_home_banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        br_home_banner.setImages(arrayListOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        //设置banner动画效果
        br_home_banner.setBannerAnimation(Transformer.Accordion)

        //设置自动轮播，默认为true
        br_home_banner.isAutoPlay(true)
        //设置轮播时间
        br_home_banner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        br_home_banner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        br_home_banner.start()
    }
    private fun initNews() {
        nv_news_flipper.buildNewsView(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }

    /*
    初始化折扣
    */
    private fun initDiscount(){
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        rv_discount_list.layoutManager = manager

        val discountAdapter  = HomeDiscountAdapter()
        rv_discount_list.adapter = discountAdapter
        discountAdapter.setNewData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }


    private fun initTopic(){
        val topicAdapter  = TopicAdapter(activity, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        vp_topic_pager.adapter=topicAdapter
        vp_topic_pager.currentItem = 1
        vp_topic_pager.offscreenPageLimit = 5

        CoverFlow.Builder().with(vp_topic_pager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
        // vp_topic_pager.setPageTransformer(true,ForegroundToBackgroundTransformer() )
        //CoverTransformer(0.3f,-30f,0f,0f)
        // vp_topic_pager.pageMargin = 30;//设置页与页之间的间距
    }

}
