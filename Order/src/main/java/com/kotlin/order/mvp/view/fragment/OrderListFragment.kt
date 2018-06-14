package com.kotlin.order.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.EventBusUtils

import com.kotlin.order.R
import com.kotlin.order.R.id.mv_multi_state_View
import com.kotlin.order.R.id.rv_order_list

import com.kotlin.order.common.OrderConstant
import com.kotlin.order.dagger.component.DaggerOrderComponent
import com.kotlin.order.event.UpdateOrderListEvent
import com.kotlin.order.mvp.model.response.OrderResponse
import com.kotlin.order.mvp.presenter.OrderListPresenter
import com.kotlin.order.mvp.presenter.view.IOrderListView
import com.kotlin.order.mvp.view.activity.OrderDetailActivity
import com.kotlin.order.mvp.view.adapter.OrderListAdapter
import com.kotlin.provider.common.ARouterPath
import com.kotlin.provider.common.ProviderConstant
import com.orhanobut.logger.Logger

import kotlinx.android.synthetic.main.fragment_order_list.*
import kotlinx.android.synthetic.main.fragment_order_list_content.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class OrderListFragment : BaseMvpFragment<OrderListPresenter>(),IOrderListView {


    private lateinit var mOrderListAdapter: OrderListAdapter
    override fun injectComponent() {

        DaggerOrderComponent.builder()
                .baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }



    override fun getContentViewResId(): Int {
        return R.layout.fragment_order_list

    }

    override fun initView() {

        rv_order_list.layoutManager=LinearLayoutManager(activity)

        mOrderListAdapter=OrderListAdapter()
        rv_order_list.adapter=mOrderListAdapter


        mOrderListAdapter.setOnItemChildClickListener { adapter, view, position ->

            when(view.id){
                R.id.btn_confirm->{

                    mPresenter.confirmOrder(mOrderListAdapter.data[position].id)
                }
                R.id.btn_cancel->{

                    showCancelDialog(mOrderListAdapter.data[position].id)

                }
                R.id.btn_pay->{
                    ARouter.getInstance().build(ARouterPath.PATH_PAY)
                            .withInt(ProviderConstant.KEY_ORDER_ID,mOrderListAdapter.data[position].id)
                            .withLong(ProviderConstant.KEY_ORDER_PRICE,mOrderListAdapter.data[position].totalPrice)
                            .navigation()
                }
            }
        }

        mOrderListAdapter.setOnItemClickListener { adapter, view, position ->

            startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to  mOrderListAdapter.data[position].id)
        }
        loadData()

    }

    /**
     * 取消订单对话框
     */
    private fun showCancelDialog(id:Int) {

        AlertView("取消订单", "确定取消订单吗?", "取消", null,  arrayOf("确定"), activity, AlertView.Style.Alert, OnItemClickListener {
            o, position ->

            if (position==0){
                mPresenter.cancelOrder(id)
            }


        }

        ).show()

    }

    private fun loadData() {
        mv_multi_state_View.showLoading()
        mPresenter.getOrderList(     arguments.getInt(OrderConstant.KEY_ORDER_STATUS,-1))
    }

    override fun onGetOrderListResult(result: MutableList<OrderResponse>?) {

        if (result!=null&&result.size>0){

            mOrderListAdapter.setNewData(result)
            mv_multi_state_View.showContent()
        }else{
            mv_multi_state_View.showEmpty()
        }
    }

    override fun onCancelOrderResult() {
        toast("取消订单成功")

        EventBusUtils.postSticky(UpdateOrderListEvent())
    }

    override fun onConfirmOrderResult() {
        toast("确认收货完成")
        EventBusUtils.postSticky(UpdateOrderListEvent())
    }

}
