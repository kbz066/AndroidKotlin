package com.kotlin.goods.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/**
 * Created by  on 2018/5/25.
 */
class GoodsListAdapter(layoutId:Int= R.layout.layout_goods_item) : BaseQuickAdapter<GoodsListResponse, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: GoodsListResponse) {

        helper.itemView.iv_goods_icon.loadImageFitCenter(item.goodsDefaultIcon)
        helper.itemView.tv_goods_desc.text=item.goodsDesc
        helper.itemView.tv_goods_price.text=YuanFenConverter.changeF2YWithUnit(item.goodsDefaultPrice)
        helper.itemView.tv_goods_sales_stock.text="销量${item.goodsSalesCount}件     库存${item.goodsStockCount}"
    }
}