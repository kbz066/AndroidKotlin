package com.kotlin.base.presenter

import android.content.Context
import com.kotlin.base.presenter.view.BaseView
import com.trello.rxlifecycle2.LifecycleProvider
import com.vondear.rxtools.RxNetTool
import javax.inject.Inject

open class BasePresenter<T:BaseView> {

    lateinit var mView:T;

    @Inject
    lateinit var rxLifecycle: LifecycleProvider<*>

    @Inject
    lateinit var mContext: Context

    /*
    检查网络是否可用
 */
    fun checkNetWork(view:BaseView):Boolean{

        if(RxNetTool.isNetworkAvailable(mContext)){
            view.showLoading()
            return true
        }
        mView.onError(1002,"网络不可用")
        return false
    }
}