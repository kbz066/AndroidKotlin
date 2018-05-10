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
import com.vondear.rxtools.RxActivityTool
import com.vondear.rxtools.RxTool
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView{


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

    }


    override fun onBackPressed() {

        val timeMillis = System.currentTimeMillis()
        // 判断当前按下的时间与上一次按下的间隔.
        if (timeMillis - mLastKeyDown >= 2000) {
            toast("连续点击两次返回键退出")
            mLastKeyDown = timeMillis;

        } else {
            RxActivityTool.AppExit(this)
        }
    }
}