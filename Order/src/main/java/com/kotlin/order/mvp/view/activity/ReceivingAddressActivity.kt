package com.kotlin.order.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.order.R

import com.kotlin.order.dagger.component.DaggerReceivingAddressComponent
import com.kotlin.order.mvp.presenter.ReceivingAddressPresenter
import com.kotlin.order.mvp.presenter.view.IReceivingAddressView


class ReceivingAddressActivity : BaseMvpActivity<ReceivingAddressPresenter>(),IReceivingAddressView {
    override fun injectComponent() {

        DaggerReceivingAddressComponent.builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_receiving_address
    }

    override fun initView() {

        loadData()

    }

    fun loadData(){


    }

}
