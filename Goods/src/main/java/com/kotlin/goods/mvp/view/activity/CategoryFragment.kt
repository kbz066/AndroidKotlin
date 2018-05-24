package com.kotlin.goods.mvp.view.activity



import com.kotlin.base.ui.fragment.BaseMvpFragment

import com.kotlin.goods.R
import com.kotlin.goods.mvp.presenter.CategoryPresenter

class CategoryFragment : BaseMvpFragment<CategoryPresenter>() {
    override fun injectComponent() {

    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {

    }




}
