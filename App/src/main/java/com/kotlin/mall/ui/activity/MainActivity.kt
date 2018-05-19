package com.kotlin.mall.ui.activity

import android.Manifest
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.mall.ui.fragment.HomeFragment
import com.vondear.rxtools.RxTool
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun initView() {
        initPermission()
        initBottomNavBar()
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }



    //购物车Tab 标签
    private var mCartBadge:TextBadgeItem? = null
    //消息Tab 标签
    private var mMsgBadge:ShapeBadgeItem? = null


    override fun getContentViewResId(): Int {
        return R.layout.activity_main

    }
    private fun initPermission() {

        //申请权限
        requestRxPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                mPermissionsListener = object :PermissionsListener{
            override fun onPermissionsFail() {

            }

            override fun onPermissionsSuccess() {
                //初始化 rxtool
                RxTool.init(this@MainActivity.applicationContext)
                initFragment();
            }

        })
    }
    /**
     * 初始化底部导航
     */
    private fun initBottomNavBar() {

        //首页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press,resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press,resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press,resources.getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        mCartBadge = TextBadgeItem()
        cartItem.setBadgeItem(mCartBadge)

        //消息
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press,resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        mMsgBadge = ShapeBadgeItem()
        mMsgBadge!!.setShape(ShapeBadgeItem.SHAPE_STAR_4_VERTICES)
        msgItem.setBadgeItem(mMsgBadge)

        //我的
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press,resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //设置底部导航模式及样式
        bv_bottom_navigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        bv_bottom_navigation.setMode(BottomNavigationBar.MODE_FIXED)
        bv_bottom_navigation.setBarBackgroundColor(R.color.common_white)
        //添加Tab
        bv_bottom_navigation
                .addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()


    }

    private fun initFragment() {
       var transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fl_contaier,HomeFragment())
        transaction.commit()

    }




}

