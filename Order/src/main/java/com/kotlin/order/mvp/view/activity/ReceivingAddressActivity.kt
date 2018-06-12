package com.kotlin.order.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.compress.CompressConfig
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.order.R
import com.kotlin.order.R.id.*

import com.kotlin.order.dagger.component.DaggerReceivingAddressComponent
import com.kotlin.order.mvp.model.response.ShipAddress
import com.kotlin.order.mvp.presenter.ReceivingAddressPresenter
import com.kotlin.order.mvp.presenter.view.IReceivingAddressView
import com.kotlin.order.mvp.view.adapter.ReceivingAddressAdapter
import com.kotlin.provider.common.ProviderConstant
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_receiving_address.*
import kotlinx.android.synthetic.main.activity_receiving_address_content.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class ReceivingAddressActivity : BaseMvpActivity<ReceivingAddressPresenter>(),IReceivingAddressView {



    private lateinit var mReceivingAddressAdapter: ReceivingAddressAdapter
    override fun injectComponent() {

        DaggerReceivingAddressComponent.builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_receiving_address
    }

    override fun onStart() {
        loadData()
        super.onStart()
    }

    override fun initView() {
        mReceivingAddressAdapter=ReceivingAddressAdapter()
        rv_address_list.layoutManager=LinearLayoutManager(this)
        rv_address_list.adapter=mReceivingAddressAdapter

        mReceivingAddressAdapter.setOnItemChildClickListener { adapter, view, position ->

            when(view.id){
                R.id.tv_delete->{
                    showSelectDialog(mReceivingAddressAdapter.data[position].id)

                }
                R.id.tv_set_default->{
                    var  item=mReceivingAddressAdapter.data[position]
                    if (item.shipIsDefault== 1){
                        item.shipIsDefault=0
                        mPresenter.setDefaultShipAddress(item)
                    }

                }
                R.id.tv_edit->{

                    EventBusUtils.postSticky(mReceivingAddressAdapter.data[position])
                    startActivity<ReceivingAddressEditActivity>()
                }
            }

        }



        btn_add_address.setOnClickListener {
            startActivity<ReceivingAddressEditActivity>()
        }


    }

    fun loadData(){

        mv_multi_state_View.showLoading()
        mPresenter.getShipAddressList()

    }
    /**
     * 选择图片对话框
     */
    private fun showSelectDialog(id:Int) {
        AlertView("删除", "确定删除收货人地址吗?", "取消", null,  arrayOf("确定"), this, AlertView.Style.Alert, OnItemClickListener {
            o, position ->

            if (position==0){
                mPresenter.deleteShipAddress(id)
            }


        }

        ).show()

    }

    /**
     * 获取收货人列表回调
     */
    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {

        if (result!=null&&result.size>0){
            mReceivingAddressAdapter.setNewData(result)
            mv_multi_state_View.showContent()
        }else{
            mv_multi_state_View.showEmpty()
        }
    }
    override fun onDeleteResult(result: String?) {
        toast("删除成功")
        loadData()
    }
    override fun onSetDefaultResult(result: String?) {

        toast("设置成功")
        loadData()
    }


}
