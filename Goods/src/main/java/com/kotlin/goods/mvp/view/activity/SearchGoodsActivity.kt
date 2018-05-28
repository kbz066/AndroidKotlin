package com.kotlin.goods.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.goods.R
import kotlinx.android.synthetic.main.activity_search_goods.*

class SearchGoodsActivity : BaseActivity() {
    override fun getContentViewResId(): Int {
        return R.layout.activity_search_goods
    }

    override fun initView() {

    }

    override fun onDestroy() {
        sv_search_goods.saveHistoryData()
        super.onDestroy()
    }


}
