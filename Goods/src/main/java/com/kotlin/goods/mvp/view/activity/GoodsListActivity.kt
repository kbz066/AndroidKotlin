package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.goods.R
import com.kotlin.goods.R.id.*
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.dagger.component.DaggerGoodsListComponent
import com.kotlin.goods.dagger.module.GoodsListModule
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.presenter.GoodsListPresenter
import com.kotlin.goods.mvp.presenter.view.IGoodsListView
import com.kotlin.goods.mvp.view.adapter.GoodsListAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_goods_list.*
import kotlinx.android.synthetic.main.activity_goods_list_context.*
import org.jetbrains.anko.startActivity

class GoodsListActivity : BaseMvpActivity<GoodsListPresenter>(),IGoodsListView,SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {



    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private lateinit var mGoodsListAdapter:GoodsListAdapter
    /**
     * 注入
     */
    override fun injectComponent() {

        DaggerGoodsListComponent.builder().baseActivityComponent(mActiviComponent)
                .goodsListModule(GoodsListModule())
                .build()
                .inject(this)

        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_goods_list
    }

    override fun initView() {

        sl_goods_refresh_layout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        sl_goods_refresh_layout.setOnRefreshListener(this)
        mGoodsListAdapter=GoodsListAdapter()
        rv_goods_list.layoutManager=GridLayoutManager(this,2)
        rv_goods_list.adapter=mGoodsListAdapter

        mGoodsListAdapter.setOnLoadMoreListener(this,rv_goods_list)

        mGoodsListAdapter.disableLoadMoreIfNotFullPage();
        mGoodsListAdapter.setOnItemClickListener {
            adapter, view, position ->

            startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to mGoodsListAdapter.data[position].id)
        }
        loadData()
    }



    fun loadData(){

        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,0)==0){
            mv_goods_multiple_status.showLoading()
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_GOODS_ID,1),mCurrentPage)
        }else{
            mv_goods_multiple_status.showLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD),mCurrentPage)
        }

    }
    /**
     * 下拉刷新
     */
    override fun onRefresh() {


        mCurrentPage=1;
        loadData()
    }

    /**
     * 上拉加载
     */
    override fun onLoadMoreRequested() {
        mCurrentPage++;
        if (mCurrentPage>mMaxPage){
            mGoodsListAdapter.loadMoreEnd();
        }else{
            loadData()
        }
    }
    /**
     * 获取商品回调
     */
    override fun onGetGoodsListResult(result: MutableList<GoodsListResponse>?) {
        if (result!=null&&result.size>0){

            mMaxPage=result[0].maxPage

            if (mCurrentPage==1){
                mGoodsListAdapter.setNewData(result)
                sl_goods_refresh_layout.isRefreshing=false

            }else{
                mGoodsListAdapter.addData(result)

            }
            mv_goods_multiple_status.showContent()
            mGoodsListAdapter.loadMoreComplete();
        }else{
            mv_goods_multiple_status.showEmpty()
        }
    }

}
