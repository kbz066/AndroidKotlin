package com.kotlin.mall.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.utils.GlideUtils
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*
import java.util.*

/**
 * Created by caofu on 2018/5/19.
 */
class HomeDiscountAdapter(): BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_home_discount_item) {


    override fun convert(helper: BaseViewHolder?, item: String?) {
       GlideUtils.loadImageFitCenter(mContext,item!!, helper!!.itemView.iv_goods_icon)

        helper.itemView.tv_discountAfter.text = "￥${Random().nextInt(800)}.00"
        helper.itemView._tvdiscountBefore.text = "$1000.00"
    }

}