package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.goods.R
import com.kotlin.goods.R.id.rv_goods_list
import com.kotlin.goods.R.id.sl_goods_refresh_layout
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.dagger.component.DaggerGoodsListComponent
import com.kotlin.goods.dagger.module.GoodsListModule
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.presenter.GoodsListPresenter
import com.kotlin.goods.mvp.presenter.view.IGoodsListView
import com.kotlin.goods.mvp.view.adapter.GoodsListAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_goods_list_context.*

class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(),IGoodsListView,SwipeRefreshLayout.OnRefreshListener {


    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private lateinit var mGoodsListAdapter:GoodsListAdapter
    /**
     * 注入
     */
    override fun injectComponent() {
        Logger.e("injectComponent")
        DaggerGoodsListComponent.builder().baseActivityComponent(mActiviComponent)
                .goodsListModule(GoodsListModule())
                .build()
                .inject(this)

        Logger.e("mPresenter--------------->"+mPresenter)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_goods_list
    }

    override fun initView() {
        Logger.e("initView")
        sl_goods_refresh_layout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        sl_goods_refresh_layout.setOnRefreshListener(this)
        mGoodsListAdapter=GoodsListAdapter()
        rv_goods_list.layoutManager=GridLayoutManager(this,2)
        rv_goods_list.adapter=mGoodsListAdapter

        loadData()
    }



    fun loadData(){

        mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_GOODS_ID,1),mCurrentPage)
    }
    /**
     * 下拉刷新
     */
    override fun onRefresh() {

        Logger.e("下拉刷新")
    }

    /**
     * 获取商品回调
     */
    override fun onGetGoodsListResult(result: MutableList<GoodsListResponse>?) {
        if (result!=null&&result.size>0){

            mGoodsListAdapter.setNewData(result)
        }
    }

}
