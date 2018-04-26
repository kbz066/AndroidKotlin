package com.kotlin.base.ui.activity

import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView

open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView{
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }
    lateinit var mpresenter:T;
}