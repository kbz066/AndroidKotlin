package com.kotlin.base.ui.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import com.kotlin.base.R
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.base.dagger.component.DaggerBaseActivityComponent
import com.kotlin.base.dagger.module.LifecycleProviderModule


import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.ui.widgets.ProgressLoadingBar
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxActivityTool
import com.vondear.rxtools.RxTool
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseFragment(),BaseView{


    /**
     * 注入mpresenter
     */

    @Inject
    lateinit var mPresenter:T;

    lateinit var mActiviComponent: BaseActivityComponent


    lateinit var mLoadingBar: ProgressLoadingBar

    var mLastKeyDown: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        mLoadingBar=ProgressLoadingBar(this.activity, R.style.LightProgressDialog)

        initActivityComponent()
        injectComponent()
        initView()
    }


    /**
     * 初始化Component
     */
    private fun initActivityComponent() {

        mActiviComponent= DaggerBaseActivityComponent.builder().baseApplicationComponent((activity.application as BaseApplication).mAppComponent)
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }



    /**
     * 初始化view
     */
    abstract fun initView()


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

    }

}