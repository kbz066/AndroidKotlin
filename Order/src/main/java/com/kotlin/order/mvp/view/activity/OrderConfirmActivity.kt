package com.kotlin.order.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.R.id.*
import com.kotlin.order.dagger.component.DaggerOrderComponent
import com.kotlin.order.event.UpdateSelectAddressEvent

import com.kotlin.order.mvp.model.response.OrderResponse
import com.kotlin.order.mvp.model.response.ShipAddress
import com.kotlin.order.mvp.presenter.OrderConfirmPresenter
import com.kotlin.order.mvp.presenter.view.IOrderConfirmView
import com.kotlin.order.mvp.view.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ARouterPath
import com.kotlin.provider.common.ProviderConstant
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxWebViewTool.loadData
import kotlinx.android.synthetic.main.activity_order_confirm.*
import kotlinx.android.synthetic.main.layout_address_item.view.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


@Route(path = ARouterPath.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(),IOrderConfirmView {



    private var mOrder:OrderResponse?=null
    private lateinit var mOrderGoodsAdapter: OrderGoodsAdapter

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var orderId:Int=0

    override fun injectComponent() {

        DaggerOrderComponent
                .builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_order_confirm
    }

    override fun initView() {
        EventBusUtils.register(this)
        setListener();
        loadData()
        initListView()
    }

    private fun initListView() {
        mOrderGoodsAdapter=OrderGoodsAdapter()
        rv_order_goods_list.layoutManager=LinearLayoutManager(this)
        rv_order_goods_list.adapter=mOrderGoodsAdapter

    }

    fun loadData(){
        mPresenter.getOrderById(orderId)
        Logger.e("orderId \t\t\t\t"+orderId)
    }

    private fun setListener() {

        tv_select_ship.setOnClickListener {
            startActivity<ReceivingAddressActivity>()
        }

        rl_ship_view.setOnClickListener {
            startActivity<ReceivingAddressActivity>()
        }
        btn_submit_order.setOnClickListener {
            mOrder?.let {
                mPresenter.submitOrder(it)
            }

        }
    }


    /**
     * 更新选择状态
     */
    fun setSelectAddressState(){

        mOrder?.let {
            if (it.shipAddress==null){
                tv_select_ship.setVisible(true)
                rl_ship_view.setVisible(false)
            }else{
                tv_select_ship.setVisible(false)
                rl_ship_view.setVisible(true)
                tv_ship_name.text= it.shipAddress!!.shipUserName + "    " + it.shipAddress!!.shipUserMobile
                tv_ship_address.text=it.shipAddress!!.shipAddress
            }
        }

    }

    @Subscribe
    fun onMessage(event: UpdateSelectAddressEvent ){
        mOrder?.let {
            it.shipAddress=event.mShipAddress
        }

        setSelectAddressState()
    }

    override fun onGetOrderByIdResult(result: OrderResponse) {
        this.mOrder=result
        mOrderGoodsAdapter.setNewData(result.orderGoodsList)

        tv_total_price.text="待支付:${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        setSelectAddressState()

    }
    override fun onSubmitOrderResult(result: String?) {

        toast("提交成功")
    }
    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }

}
