package com.kotlin.mall.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getContentViewResId(): Int {
        return R.layout.activity_main

        initBottomNavBar()
    }
    /**
     * 初始化底部导航
     */
    private fun initBottomNavBar() {

        var homeItem=BottomNavigationItem(R.drawable.btn_nav_home_press,"主页")
                .setInactiveIconResource(com.kotlin.base.R.drawable.btn_nav_home_normal)
                .setActiveColorResource(com.kotlin.base.R.color.common_blue)
                .setInActiveColorResource(com.kotlin.base.R.color.text_normal)


    }


}
