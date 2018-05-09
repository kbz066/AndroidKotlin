package com.kotlin.base.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.kotlin.base.R

/**
 * Created by  on 2018/5/9.
 */
class HeaderToolBar: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){

        initView(context)
    }

    private fun initView(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.layout_header_bar, this, true);
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


}