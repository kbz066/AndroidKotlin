package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.goods.R
import com.kotlin.goods.mvp.view.fragment.CartMainFragment
import com.orhanobut.logger.Logger

class CartMainActivity : BaseActivity() {
    override fun getContentViewResId(): Int {
        return R.layout.activity_cart_main
    }

    override fun initView() {
        val fragment = fragmentManager.findFragmentById(R.id.ft_fragment_cart)

        (fragment as CartMainFragment).showCloseBar()
    }


}
