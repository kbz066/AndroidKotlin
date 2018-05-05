package com.kotlin.base.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView

abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getContentViewResId())
        injectComponent()
        initView()
    }

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 子类提供ContentView
     */
    abstract fun getContentViewResId():  Int

    /**
     * 依赖注入
     */
    abstract fun injectComponent();


    override fun showLoading() {


    }

    override fun hideLoading() {
    }

    override fun onError() {
    }
    lateinit var mpresenter:T;
}