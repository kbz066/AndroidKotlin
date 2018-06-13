package com.kotlin.mall.ui.activity

import android.Manifest
import android.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.mvp.view.fragment.CartMainFragment
import com.kotlin.goods.mvp.view.fragment.CategoryFragment
import com.kotlin.mall.R
import com.kotlin.mall.R.id.bv_bottom_navigation
import com.kotlin.mall.ui.fragment.HomeFragment
import com.kotlin.mall.ui.fragment.MeFragment
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxActivityTool
import com.vondear.rxtools.RxSPTool
import com.vondear.rxtools.RxTool
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.toast
import java.util.*


class MainActivity : BaseActivity() {


    //Fragment 栈管理
    private val mStack = Stack<Fragment>()
    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }
    //商品分类Fragment
    private val mCategoryFragment by lazy { CategoryFragment() }
    //购物车Fragment
    private val mCartFragment by lazy { CartMainFragment() }
    //消息Fragment
    private val mMsgFragment by lazy { HomeFragment() }
    //"我的"Fragment
    private val mMeFragment by lazy { MeFragment() }



    private var mLastKeyDown: Long = 0
    //购物车Tab 标签
    private var mCartBadge:TextBadgeItem? = null
    //消息Tab 标签
    private var mMsgBadge:ShapeBadgeItem? = null

    override fun initView() {


        EventBusUtils.register(this)
        initPermission()
        initBottomNavBar()
        loadCartSize()


    }







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
        bv_bottom_navigation.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })

    }

    /**
     * 设置MsgBadget显示
     */
    fun setMsgBadget(flag:Boolean){
        if (flag){
            mMsgBadge!!.show()
        }else{
            mMsgBadge!!.hide()
        }
    }

    /**
     * 检查购物车Tab是否显示标签
     */
    fun checkCartBadge(count:Int){
        if (count<=0){
            mCartBadge!!.hide()
        }else{
            mCartBadge!!.show()
            mCartBadge!!.setText("$count")
        }
    }

    private fun initFragment() {


        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fl_contaier,mHomeFragment)
        transaction.add(R.id.fl_contaier,mCategoryFragment)
        transaction.add(R.id.fl_contaier,mCartFragment)
        transaction.add(R.id.fl_contaier,mMsgFragment)
        transaction.add(R.id.fl_contaier,mMeFragment)
        transaction.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)

        changeFragment(0)

    }

    /*
        切换Tab，切换对应的Fragment
     */
    private fun changeFragment(position: Int) {
        val manager = fragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }


    @Subscribe()
    fun onMessageEvent(event: UpdateCartSizeEvent){


        loadCartSize()

    }

    private fun loadCartSize() {


        checkCartBadge(RxSPTool.getInt(this, GoodsConstant.SP_CART_SIZE))
    }

    override fun onBackPressed() {

        val timeMillis = System.currentTimeMillis()
        // 判断当前按下的时间与上一次按下的间隔.
        if (timeMillis - mLastKeyDown >= 2000) {
            toast("连续点击两次返回键退出")

            mLastKeyDown = timeMillis;

        } else {
            RxActivityTool.AppExit(this)
        }
    }

    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }

}

