package com.kotlin.goods.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.goods.R
import com.kotlin.goods.R.id.fl_sku_content_view
import com.kotlin.goods.R.id.tv_sku_title
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.event.UpdateSkuTxtEvent
import com.kotlin.goods.mvp.model.response.GoodsSkuResponse
import com.orhanobut.logger.Logger
import com.zhy.view.flowlayout.FlowLayout
import kotlinx.android.synthetic.main.layout_sku_view.view.*
import com.zhy.view.flowlayout.TagAdapter



/**
 * Created by  on 2018/5/31.
 */
class SkuView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {


    private lateinit var goodsSku:GoodsSkuResponse
    init {
        View.inflate(context, R.layout.layout_sku_view,this)


    }




    fun addChildToSkuLayout(goodsSku: GoodsSkuResponse){

        this.goodsSku=goodsSku
        tv_sku_title.text=goodsSku.skuTitle

        fl_sku_content_view.adapter = object : TagAdapter<String>(goodsSku.skuContent) {
            override fun getView(parent: FlowLayout, position: Int, s: String): View {
                val tv = LayoutInflater.from(context).inflate(R.layout.layout_sku_item, fl_sku_content_view, false) as TextView
                tv.text = s
                return tv
            }
        }
        fl_sku_content_view.setOnTagClickListener { view, position, parent ->
            EventBusUtils.post(UpdateSkuTxtEvent())
            true
        }
    }



    fun getSelectTxt():String?{


        var index=fl_sku_content_view.selectedList.firstOrNull()
        return if (index!=null){
            goodsSku.skuContent[index]
        }else{
            null
        }

    }


}