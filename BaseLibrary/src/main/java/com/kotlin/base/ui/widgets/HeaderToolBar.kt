package com.kotlin.base.ui.widgets

import android.app.Activity
import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.kotlin.base.R
import com.kotlin.base.R.id.*


import com.vondear.rxtools.RxActivityTool
import kotlinx.android.synthetic.main.layout_header_bar.view.*


/**
 * Created by  on 2018/5/9.
 */
class HeaderToolBar: RelativeLayout {


    //是否显示"返回"图标
    private var isShowBack = true
    //Title文字
    private var titleText:String? = null

    private var rightText:String?=null
    private  var mBackClickListener:OnClickListener?=null


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){

        initView(context,attrs)
    }



    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initView(context: Context?,attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_header_bar, this, true);


        val typedArray = context!!.obtainStyledAttributes(attrs,R.styleable.HeaderBar)
        titleText=typedArray.getString(R.styleable.HeaderBar_titleText)

        rightText=typedArray.getString(R.styleable.HeaderBar_rightText)
        isShowBack=typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true)



        iv_back_image.visibility=if (isShowBack) View.VISIBLE else View.GONE

        titleText?.let {
            tv_bar_title.text=it
        }

        rightText?.let {
            tv_bar_right_txt.text=it
        }

        //设置事件

        iv_back_image.setOnClickListener{

            if (mBackClickListener!=null){
                mBackClickListener!!.onClick(it)
            }else{
                RxActivityTool.finishActivity(context as Activity)
            }

        }



    }

    fun getToolBar():Toolbar{
        return tb_header_bar
    }

    fun setBackClickListener(listener:OnClickListener){
        this.mBackClickListener=listener

    }

    fun getRightView():TextView{
        return tv_bar_right_txt
    }

    fun hideCloseBar(){
        iv_back_image.visibility=View.GONE
    }
    fun showCloseBar(){
        iv_back_image.visibility=View.VISIBLE
    }
}