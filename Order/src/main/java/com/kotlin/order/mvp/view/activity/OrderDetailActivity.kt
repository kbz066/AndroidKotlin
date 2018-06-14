package com.kotlin.order.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.dagger.component.DaggerOrderComponent
import com.kotlin.order.mvp.model.response.OrderResponse
import com.kotlin.order.mvp.presenter.OrderDetailPresenter
import com.kotlin.order.mvp.presenter.view.IOrderDetailView
import com.kotlin.order.mvp.view.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant

import kotlinx.android.synthetic.main.activity_order_detail.*
import org.jetbrains.anko.toast

class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(),IOrderDetailView {



    private lateinit var mOrderGoodsAdapter: OrderGoodsAdapter


    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var orderId=0

    override fun injectComponent() {
        DaggerOrderComponent
                .builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_order_detail
    }

    override fun initView() {
        mOrderGoodsAdapter=OrderGoodsAdapter()
        rv_order_goods_list.layoutManager= LinearLayoutManager(this)
        rv_order_goods_list.adapter=mOrderGoodsAdapter
        loadData()
    }
    fun loadData(){
        mPresenter.getOrderById(orderId)
    }

    override fun onGetOrderByIdResult(result: OrderResponse) {
        result.shipAddress?.let {
            tv_content_ship_name.text = it.shipUserName
            tv_content_ship_mobile.text = it.shipUserMobile
            tv_content_ship_address.text = it.shipAddress
        }
        mOrderGoodsAdapter.setNewData(result.orderGoodsList)

    }

}
