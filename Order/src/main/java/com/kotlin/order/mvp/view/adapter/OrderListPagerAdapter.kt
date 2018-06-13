package com.kotlin.order.mvp.view.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v13.app.FragmentStatePagerAdapter
import android.system.OsConstants
import android.view.View
import com.kotlin.order.common.OrderConstant

import com.kotlin.order.mvp.view.fragment.OrderListFragment
import com.orhanobut.logger.Logger


/**
 * Created by  on 2018/5/30.
 */
class OrderListPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    private val mTitle= arrayOf("全部","待付款","待收货","已完成","已取消")



    override fun getItem(position: Int): Fragment {
        var mFragment= OrderListFragment()

        var mBundle=Bundle()
        mBundle.putInt(OrderConstant.KEY_ORDER_STATUS,position)
        mFragment.arguments=mBundle
        return mFragment

    }

    override fun getCount(): Int {

        return mTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }

    override fun instantiateItem(container: View, position: Int): Any {

        return super.instantiateItem(container, position)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}