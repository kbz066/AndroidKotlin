package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.goods.R
import com.kotlin.goods.R.id.*
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.mvp.view.adapter.GoodsDetailPagerAdapter
import com.kotlin.goods.widget.GoodsSkuPopView
import com.kotlin.provider.common.afterLogin
import com.vondear.rxtools.RxSPTool
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

class GoodsDetailActivity : BaseActivity() ,View.OnClickListener{
    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_back->{
                finish()
            }
            R.id.tv_enter_cart->{
                startActivity<CartMainActivity>()
            }
            R.id.bt_add_cart->{
                afterLogin {
                    EventBusUtils.post(AddCartEvent())
                }
            }
        }
    }


    private lateinit var mCartBadgeView: QBadgeView

    override fun getContentViewResId(): Int {
        return R.layout.activity_goods_detail
    }

    override fun initView() {
        EventBusUtils.register(this)
        vp_goods_detail_pager.adapter= GoodsDetailPagerAdapter(fragmentManager)
        tl_goods_detail_Tab.tabMode=TabLayout.MODE_FIXED
        tl_goods_detail_Tab.setupWithViewPager(vp_goods_detail_pager)

        iv_back.setOnClickListener(this)
        tv_enter_cart.setOnClickListener(this)

         bt_add_cart.setOnClickListener(this)

        mCartBadgeView= QBadgeView(this)

        loadCartSize()
    }


    fun loadCartSize(){
        mCartBadgeView.bindTarget(tv_enter_cart)
        mCartBadgeView.badgeGravity = Gravity.END or    Gravity.TOP
        mCartBadgeView.setGravityOffset(10f,-2f,true)

        RxSPTool.getInt(this,GoodsConstant.SP_CART_SIZE).let {
            if (it>0){
                mCartBadgeView.showContextMenu()
                mCartBadgeView.badgeNumber =it
            }
        }


    }
    @Subscribe()
    fun onMessageEvent(event: UpdateCartSizeEvent){

        loadCartSize()

    }


    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }


}
