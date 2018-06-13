package com.kotlin.order.mvp.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.base.ext.setVisible
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.R
import com.kotlin.order.common.OrderConstant


import com.kotlin.order.mvp.model.response.OrderResponse
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_order_confirm.view.*
import kotlinx.android.synthetic.main.layout_order_item.view.*
import org.jetbrains.anko.dip

/**
 * Created by  on 2018/6/13.
 */
class OrderListAdapter (layoutId:Int= R.layout.layout_order_item) : BaseQuickAdapter<OrderResponse, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: OrderResponse) {


        helper.addOnClickListener(R.id.btn_confirm)
        helper.addOnClickListener(R.id.btn_pay)

        helper.addOnClickListener(R.id.btn_cancel)

        Logger.e("item.orderStatus${helper.layoutPosition} \t\t\t"+item.orderStatus)

        var mTotalCount = 0
        if (item.orderGoodsList.size==1){

            helper.itemView.ll_multi_goods.visibility=View.GONE
            helper.itemView.rl_single_goods_view.visibility=View.VISIBLE

            helper.itemView.iv_goods_icon.loadImageFitCenter(item.orderGoodsList[0].goodsIcon)

            helper.itemView.tv_goods_desc.text=item.orderGoodsList[0].goodsDesc
            helper.itemView.tv_goods_price.text=YuanFenConverter.changeF2YWithUnit(item.totalPrice)
            helper.itemView.tv_goods_count.text="x${item.orderGoodsList[0].goodsCount}"
            mTotalCount=item.orderGoodsList[0].goodsCount
        }else{



            helper.itemView.ll_multi_goods.visibility=View.VISIBLE
            helper.itemView.rl_single_goods_view.visibility=View.GONE
            for (orderGoods in item.orderGoodsList){//动态添加商品视图
                val imageView = ImageView(mContext)
                val lp = ViewGroup.MarginLayoutParams(mContext.dip(60.0f),mContext.dip(60.0f))
                lp.rightMargin = mContext.dip(15f)
                imageView.layoutParams = lp
                imageView.loadImageFitCenter(orderGoods.goodsIcon)

                helper.itemView.ll_multi_goods.addView(imageView)

                mTotalCount += orderGoods.goodsCount
            }
        }

        helper.itemView.tv_order_Info.text = "合计${mTotalCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(item.totalPrice)}"



        when(item.orderStatus){


            OrderConstant.ORDER_WAIT_PAY->{
                helper.itemView.tv_order_status_name.setTextColor(mContext.resources.getColor(R.color.common_red))
                helper.itemView.tv_order_status_name.text="待支付"
                setBtnState(false,true,true,helper)
            }
            OrderConstant.ORDER_WAIT_CONFIRM->{
                helper.itemView.tv_order_status_name.setTextColor(mContext.resources.getColor(R.color.common_blue))
                helper.itemView.tv_order_status_name.text="待收货"
                setBtnState(true,false,true,helper)
            }
            OrderConstant.ORDER_CANCELED->{
                helper.itemView.tv_order_status_name.setTextColor(mContext.resources.getColor(R.color.common_gray))

                helper.itemView.tv_order_status_name.text="已取消"
                setBtnState(false,false,false,helper)
            }

            OrderConstant.ORDER_COMPLETED->{
                helper.itemView.tv_order_status_name.setTextColor(mContext.resources.getColor(R.color.common_yellow))

                helper.itemView.tv_order_status_name.text="已完成"
                setBtnState(false,false,false,helper)
            }

        }
    }

    fun  setBtnState(confirmVisible: Boolean, waitPayVisible: Boolean, cancelVisible: Boolean,holder: BaseViewHolder){

        holder.itemView.btn_confirm.setVisible(confirmVisible)
        holder.itemView.btn_pay.setVisible(waitPayVisible)
        holder.itemView.btn_cancel.setVisible(cancelVisible)

        if (confirmVisible || waitPayVisible || cancelVisible){
            holder.itemView.ll_bottom_view.setVisible(true)
        }else{
            holder.itemView.ll_bottom_view.setVisible(false)
        }



    }

}