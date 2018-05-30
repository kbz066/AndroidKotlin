package com.kotlin.goods.mvp.view.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter
import com.kotlin.goods.mvp.view.fragment.GoodsDetailTabOneFragment
import com.kotlin.goods.mvp.view.fragment.GoodsDetailTabTwoFragment


/**
 * Created by  on 2018/5/30.
 */
class GoodsDetailPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    private val mTitle= arrayOf("商品","详情")

    private val mTabOneFragment:GoodsDetailTabOneFragment by lazy { GoodsDetailTabOneFragment() }

    private val mTabTwoFragment: GoodsDetailTabTwoFragment by lazy { GoodsDetailTabTwoFragment() }

    override fun getItem(position: Int): Fragment {
        return if (position==0){
            mTabOneFragment
        }else{
            mTabTwoFragment
        }
    }

    override fun getCount(): Int {

        return mTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }
}