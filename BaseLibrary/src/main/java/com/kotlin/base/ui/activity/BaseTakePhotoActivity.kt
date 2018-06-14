package com.kotlin.base.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import android.util.Log
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.model.TResult
import com.kotlin.base.R
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.dagger.component.BaseActivityComponent
import com.kotlin.base.dagger.component.DaggerBaseActivityComponent
import com.kotlin.base.dagger.module.LifecycleProviderModule


import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.ui.widgets.ProgressLoadingBar
import com.orhanobut.logger.Logger

import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseTakePhotoActivity<T:BasePresenter<*>>:BaseActivity(),BaseView, TakePhoto.TakeResultListener{


    /**
     * 注入mpresenter
     */

    @Inject
    lateinit var mPresenter:T;

    lateinit var mActiviComponent: BaseActivityComponent


    lateinit var mLoadingBar: ProgressLoadingBar



    val mTakePhoto:TakePhoto by lazy { TakePhotoImpl(this,this) }
    var mLastKeyDown: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        mTakePhoto.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)



        mLoadingBar=ProgressLoadingBar(this, R.style.LightProgressDialog)

        initActivityComponent()
        injectComponent()

    }






    override fun onSaveInstanceState(outState: Bundle?) {
        mTakePhoto.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    /*
        获取图片，成功回调
     */
    override fun takeSuccess(result: TResult?) {

    }

    /*
        获取图片，取消回调
     */
    override fun takeCancel() {
    }

    /*
        获取图片，失败回调
     */
    override fun takeFail(result: TResult?, msg: String?) {

        toast("选择图片失败$msg")

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

//
//    override fun onBackPressed() {
//
//        val timeMillis = System.currentTimeMillis()
//        // 判断当前按下的时间与上一次按下的间隔.
//        if (timeMillis - mLastKeyDown >= 2000) {
//            toast("连续点击两次返回键退出")
//
//            mLastKeyDown = timeMillis;
//
//        } else {
//            RxActivityTool.AppExit(this)
//        }
//    }
}