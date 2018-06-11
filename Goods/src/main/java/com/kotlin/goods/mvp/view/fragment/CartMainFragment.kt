package com.kotlin.goods.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.ext.setVisible
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.R.id.*
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.dagger.component.DaggerCartComponent
import com.kotlin.goods.event.UpdateAllCheckEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.mvp.model.request.SubmitCartRequest
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import com.kotlin.goods.mvp.presenter.CartPresenter
import com.kotlin.goods.mvp.presenter.view.ICartView
import com.kotlin.goods.mvp.view.adapter.CartAdapter
import com.kotlin.provider.common.ARouterPath
import com.kotlin.provider.common.ProviderConstant
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxSPTool
import kotlinx.android.synthetic.main.fragment_cart_main.*
import kotlinx.android.synthetic.main.fragment_cart_main_content.*

import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.toast


class CartMainFragment : BaseMvpFragment<CartPresenter>(),ICartView{



    private var totalPrice=0L;
    private lateinit var mCartAdapter: CartAdapter


    override fun getContentViewResId(): Int {
        return R.layout.fragment_cart_main
    }
    override fun injectComponent() {

        DaggerCartComponent.builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun onStart() {
        loadCartList()
        super.onStart()
    }
    override fun initView() {
        EventBusUtils.register(this)

        mCartAdapter=CartAdapter()
        rv_cart_list.layoutManager=LinearLayoutManager(activity)
        rv_cart_list.adapter=mCartAdapter



        //设置事件
        initListener()


    }

    private fun initListener() {
        //全选/不选
        cb_all_checked.setOnClickListener {
            mCartAdapter.data.forEach {
                it.isSelected=cb_all_checked.isChecked
            }
            mCartAdapter.notifyDataSetChanged()
            updateCartPrice()
        }
        tb_cart_bar.getRightView().setOnClickListener {
            updateEditStatus()
        }

        btn_delete.setOnClickListener {
            var delList=mCartAdapter.data
                    .filter { it.isSelected }
                    .map {
                        it.id
                    }
            mPresenter.deleteCart(delList)
        }

        btn_settle_accounts.setOnClickListener {
            var list= mutableListOf<CartGoodsResponse>()
                    mCartAdapter.data
                    .filter { it.isSelected }
                    .mapTo(list){
                        it
                    }
            if (list.size==0){
                toast("请选择商品")
                return@setOnClickListener
            }

            mPresenter.submitCart(list,totalPrice)

        }
    }

    /**
     * 更新编辑按钮状态
     */
    private fun updateEditStatus() {
        if (getString(R.string.common_edit)==tb_cart_bar.getRightView().text.toString()){//编辑

            tb_cart_bar.getRightView().text=getString(R.string.common_complete)
            btn_settle_accounts.visibility=View.GONE
            tv_total_price.visibility=View.GONE
            btn_delete.visibility=View.VISIBLE

        }else{
            tb_cart_bar.getRightView().text=getString(R.string.common_edit)
            btn_settle_accounts.visibility=View.VISIBLE
            tv_total_price.visibility=View.VISIBLE
            btn_delete.visibility=View.GONE
        }
    }


    /**
     * 商品总价
     */
    fun updateCartPrice(){

        totalPrice=mCartAdapter.data
                .filter {
                    it.isSelected
                }
                .map { it.goodsCount*it.goodsPrice }
                .sum()
        tv_total_price.text = "合计:${YuanFenConverter.changeF2YWithUnit(totalPrice)}"
    }
    /**
     * 更新全选按钮
     */
    @Subscribe()
    fun onMessageEvent(event: UpdateAllCheckEvent){
        cb_all_checked.isChecked=event.isAllCheck
        updateCartPrice()
    }



    /**
     * 加载购物车数据
     */
    fun loadCartList(){

        mv_multi_state_View.showLoading()
        mPresenter.getCartList()

    }




    fun showCloseBar() {

        tb_cart_bar.showCloseBar()
    }
    override fun onGetCartListResult(data: MutableList<CartGoodsResponse>?) {

        if (data!=null&&data.size>0){
            tb_cart_bar.getRightView().setVisible(true)
            mCartAdapter.setNewData(data)
            mv_multi_state_View.showContent()
            updateCartPrice()

        }else{
            tb_cart_bar.getRightView().setVisible(false)
            mv_multi_state_View.showEmpty()
        }
        RxSPTool.putInt(activity, GoodsConstant.SP_CART_SIZE,data?.size?:0)

        EventBusUtils.post(UpdateCartSizeEvent())
    }

    override fun onDeleteCartListResult(data: String) {

        loadCartList()
    }


    /**
     * 结算回调
     */
    override fun onSubmitCartListResult(orderId: Int) {


        ARouter.getInstance().build(ARouterPath.PATH_ORDER_CONFIRM).withInt(ProviderConstant.KEY_ORDER_ID,orderId).navigation()



    }
    override fun onDestroy() {
        EventBusUtils.unregister(this)
        super.onDestroy()
    }

}
