package com.kotlin.goods.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.event.UpdateAllCheckEvent
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

/**
 * Created by  on 2018/6/4.
 */
class CartAdapter(layoutId:Int= R.layout.layout_cart_goods_item) : BaseQuickAdapter<CartGoodsResponse, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: CartGoodsResponse) {


        item.goodsIcon?.let {
            helper.itemView.iv_goods_icon.loadImageFitCenter(it)
        }
        helper.itemView.cb_checked.isChecked=item.isSelected
        helper.itemView.tv_goods_desc.text=item.goodsDesc
        helper.itemView.tv_goods_sku.text=item.goodsSku
        helper.itemView.tv_goods_price.text = YuanFenConverter.changeF2YWithUnit(item.goodsPrice)
        helper.itemView.nb_goods_count.setCurrentNumber(item.goodsCount)

        helper.itemView.cb_checked.setOnClickListener {

            item.isSelected=helper.itemView.cb_checked.isChecked
            EventBusUtils.post(UpdateAllCheckEvent(data.all { it.isSelected }))
//            setData(helper.layoutPosition,item)

        }

    }
}