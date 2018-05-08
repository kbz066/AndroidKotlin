package com.kotlin.base.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.base.dagger.component.DaggerBaseActivityComponent
import com.kotlin.base.dagger.module.LifecycleProviderModule


import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.ui.widgets.ProgressLoadingBar
import javax.inject.Inject

abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView{


    /**
     * 注入mpresenter
     */

    @Inject
    lateinit var mpresenter:T;

    lateinit var mActiviComponent: BaseActivityComponent


    lateinit var mLoadingBar: ProgressLoadingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getContentViewResId())
        mLoadingBar=ProgressLoadingBar.create(this)
        initActivityComponent()
        injectComponent()
        initView()
    }

    /**
     * 初始化Component
     */
    private fun initActivityComponent() {

        mActiviComponent= DaggerBaseActivityComponent.builder().baseApplicationComponent((application as BaseApplication).mAppComponent)
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
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
        mLoadingBar.showLoadingBar()

    }

    override fun hideLoading() {
        mLoadingBar.dismissLoadingBar()
    }

    override fun onError(statusCode: Int, msg: String?) {
       // mLoadingBar.dismissLoadingBar()
    }

}