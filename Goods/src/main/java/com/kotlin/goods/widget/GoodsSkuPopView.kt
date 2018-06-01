package com.kotlin.goods.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant


import com.kotlin.goods.mvp.model.response.GoodsSkuResponse
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.layout_sku_pop.view.*


/**
 * Created by  on 2018/5/30.
 */
class GoodsSkuPopView(var mContext:Context) : PopupWindow(mContext) ,View.OnClickListener{

    private  var mSkuViewList= arrayListOf<SkuView>()

    init {

        var view=LayoutInflater.from(mContext).inflate(R.layout.layout_sku_pop,null)
        this.contentView=view

        initView()

        // 设置弹出窗体的宽
        this.width = ViewGroup.LayoutParams.MATCH_PARENT
        // 设置弹出窗体的高
        this.height = ViewGroup.LayoutParams.MATCH_PARENT

        //弹出销毁 动画
        this.animationStyle=R.style.AnimBottom
        this.isFocusable = true
        this. background.alpha = 150

        view.setOnTouchListener { v, event ->
            var top=view.rl_pop_bg_view.top

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.y<top){
                    dismiss()
                }
            }

            true
        }

    }

    private fun initView() {
        contentView.ll_sku_group_view.addView(SkuView(context =mContext))
        contentView.iv_goods_close_icon.setOnClickListener(this)
        contentView.mSkuCountBtn.setCurrentNumber(1)
    }


    override fun onClick(v: View) {

        when(v.id){
            R.id.iv_goods_close_icon->{
                dismiss()
            }
        }

    }

    /*
    设置商品图标
 */
    fun setGoodsIcon(text: String) {
        contentView.iv_goods_icon.loadImageFitCenter(text)
    }

    /*
        设置商品价格
     */
    fun setGoodsPrice(text: Long) {
        contentView.tv_goods_price.text = YuanFenConverter.changeF2YWithUnit(text)
    }

    /*
        设置商品编号
     */
    fun setGoodsCode(text: String) {
        contentView.tv_goods_code.text = "商品编号:$text"
    }

    fun addSkuView(goodsSku:List<GoodsSkuResponse>){


        goodsSku.forEach {

            var itemSkuView=SkuView(context = mContext)

            itemSkuView.addChildToSkuLayout(it)
            mSkuViewList.add(itemSkuView)
            contentView.ll_sku_group_view.addView(itemSkuView)

        }
    }

    /*
    获取商品数量
 */
    fun getSelectCount() = contentView.mSkuCountBtn.number

    fun getSelectSku():String{
        var skuTxt:String=""
        mSkuViewList.forEach {


            it.getSelectTxt()?.let {
                skuTxt+=it+GoodsConstant.SKU_SEPARATOR
            }

        }
        return skuTxt.take(skuTxt.length-1)
    }



}