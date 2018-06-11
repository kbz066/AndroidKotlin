package com.kotlin.order.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.mvp.model.response.OrderGoods
import kotlinx.android.synthetic.main.layout_order_goods_item.view.*

/**
 * Created by  on 2018/6/11.
 */
class OrderGoodsAdapter (layoutId:Int= R.layout.layout_order_goods_item) : BaseQuickAdapter<OrderGoods, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: OrderGoods) {
        helper.itemView.iv_goods_icon.loadImageFitCenter(item.goodsIcon)
        helper.itemView.tv_goods_desc.text=item.goodsDesc
        helper.itemView.tv_goods_sku.text=item.goodsSku
        helper.itemView.tv_goods_price.text= YuanFenConverter.changeF2YWithUnit(item.goodsPrice)
        helper.itemView.tv_goods_count.text="x${item.goodsCount}"

    }
}