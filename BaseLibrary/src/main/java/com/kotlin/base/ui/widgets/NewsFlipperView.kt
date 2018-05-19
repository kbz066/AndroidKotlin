package com.kotlin.base.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.kotlin.base.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.layout_news_flipper.view.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.find
import org.jetbrains.anko.px2sp

/**
 * Created by  on 2018/5/19.
 */
class NewsFlipperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {


    private val mFlipperView: ViewFlipper
    init {

        var  view= LayoutInflater.from(context).inflate(R.layout.layout_news_flipper,null)
        mFlipperView=view.find(R.id.vf_news_flipper)
        mFlipperView.setInAnimation(context,R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context,R.anim.news_bottom_out)
        addView(view)
    }

    fun buildNewsView(string: Array<String>){
        string.forEach {
            var view:TextView=TextView(context)
            view.text=it
            view.textSize = px2sp(dimen(R.dimen.text_small_size))
            view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            mFlipperView.addView(view)
        }
        mFlipperView.startFlipping()
    }




}