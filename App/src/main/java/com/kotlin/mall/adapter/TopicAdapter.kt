package com.kotlin.mall.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kotlin.base.ext.loadImageRoundedCorners
import com.kotlin.base.ext.loadUrlImage
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * Created by  on 2018/5/21.
 */
class TopicAdapter(private var mContext:Context,private var urlPath:List<String>) : PagerAdapter() {



    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return urlPath.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_topic_item, null)
        rooView.iv_topic_image.loadImageRoundedCorners(urlPath[position])
        container.addView(rooView)
        return rooView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}