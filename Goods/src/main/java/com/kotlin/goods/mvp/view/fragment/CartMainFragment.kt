package com.kotlin.goods.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.goods.R
import com.kotlin.goods.R.id.tb_cart_bar
import com.kotlin.goods.dagger.component.DaggerCartComponent
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import com.kotlin.goods.mvp.presenter.CartPresenter
import com.kotlin.goods.mvp.presenter.view.ICartView
import com.kotlin.goods.mvp.view.adapter.CartAdapter
import kotlinx.android.synthetic.main.fragment_cart_main.*
import kotlinx.android.synthetic.main.fragment_cart_main_content.*


class CartMainFragment : BaseMvpFragment<CartPresenter>(),ICartView{



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
        }
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

            mCartAdapter.setNewData(data)
            mv_multi_state_View.showContent()
        }else{
            mv_multi_state_View.showEmpty()
        }
    }

}
