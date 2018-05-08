package com.kotlin.base.presenter

import com.kotlin.base.presenter.view.BaseView
import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T:BaseView> {

    lateinit var mView:T;

    @Inject
    lateinit var rxLifecycle: LifecycleProvider<*>
}