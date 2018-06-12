package com.kotlin.order.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.order.R
import com.kotlin.order.mvp.model.response.ShipAddress
import kotlinx.android.synthetic.main.layout_address_item.view.*

/**
 * Created by  on 2018/6/11.
 */
class ReceivingAddressAdapter(layoutId:Int= R.layout.layout_address_item): BaseQuickAdapter<ShipAddress, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: ShipAddress) {

        helper.itemView.tv_ship_name.text=item.shipUserName + "    " + item.shipUserMobile

        helper.itemView.tv_ship_address.text=item.shipAddress
        helper.itemView.tv_set_default.isSelected= item.shipIsDefault==0

        helper.addOnClickListener(R.id.tv_delete)
        helper.addOnClickListener(R.id.tv_set_default)
        helper.addOnClickListener(R.id.tv_edit)

    }
}