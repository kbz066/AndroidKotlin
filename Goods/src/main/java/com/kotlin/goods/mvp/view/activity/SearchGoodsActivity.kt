package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle

import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.goods.R
import com.kotlin.goods.R.id.sv_search_goods
import com.kotlin.goods.common.GoodsConstant
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity

class SearchGoodsActivity : BaseActivity() {
    override fun getContentViewResId(): Int {
        return R.layout.activity_search_goods
    }

    override fun initView() {
        sv_search_goods.setSearchFun( ::startSearch)
    }

    override fun onDestroy() {
        sv_search_goods.saveHistoryData()
        super.onDestroy()
    }

    /**
     * 开始搜索
     */
    fun startSearch(key:String){


        startActivity<GoodsListActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to key)
    }


}
