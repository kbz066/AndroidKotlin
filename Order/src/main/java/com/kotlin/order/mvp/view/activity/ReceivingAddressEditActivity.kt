package com.kotlin.order.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.order.R
import com.kotlin.order.R.id.*
import com.kotlin.order.dagger.component.DaggerReceivingAddressComponent
import com.kotlin.order.mvp.model.response.ShipAddress
import com.kotlin.order.mvp.presenter.EditReceivingAddressPresenter
import com.kotlin.order.mvp.presenter.ReceivingAddressPresenter
import com.kotlin.order.mvp.presenter.view.IEditReceivingAddressView
import com.kotlin.order.mvp.presenter.view.IReceivingAddressView
import com.vondear.rxtools.RxWebViewTool.loadData
import kotlinx.android.synthetic.main.activity_receiving_address_edit.*
import org.jetbrains.anko.toast

class ReceivingAddressEditActivity : BaseMvpActivity<EditReceivingAddressPresenter>(),IEditReceivingAddressView {



    private  var mShipAddress:ShipAddress?=null
    override fun injectComponent() {

        DaggerReceivingAddressComponent.builder()
                .baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {

        return R.layout.activity_receiving_address_edit
    }

    override fun initView() {

        btn_save.setOnClickListener {

            if (et_ship_name.text.isNullOrEmpty()){
                toast("请输入收货人姓名")
                return@setOnClickListener
            }
            if (et_ship_mobile.text.isNullOrEmpty()){
                toast("请输入手机号码")
                return@setOnClickListener
            }
            if (et_ship_address.text.isNullOrEmpty()){
                toast("请输入收货人地址")
                return@setOnClickListener
            }

            if (mShipAddress==null){
                mPresenter.addShipAddress(et_ship_name.text.toString(),et_ship_mobile.text.toString(),et_ship_address.text.toString())

            }else{
                mShipAddress?.let {
                    it.shipAddress=et_ship_address.text.toString()
                    it.shipUserName=et_ship_name.text.toString()
                    it.shipUserMobile=et_ship_mobile.text.toString()
                    mPresenter.editShipAddress(it)

                }


            }

        }
        mShipAddress=EventBusUtils.getStickyEvent(ShipAddress::class.java)
        mShipAddress?.let {
            et_ship_name.setText(it.shipUserName)
            et_ship_mobile.setText(it.shipUserMobile)
            et_ship_address.setText(it.shipAddress)
        }
    }
    override fun onAddShipAddressResult(result: String?) {
        toast("添加成功")
        finish()
    }
    override fun onEditAddressResulresult(result: String?) {

        toast("修改成功")
        finish()
    }

}
