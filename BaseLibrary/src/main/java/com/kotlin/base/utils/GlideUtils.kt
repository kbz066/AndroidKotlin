package com.kotlin.base.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform


import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kotlin.base.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/*
    Glide工具类
 */
object GlideUtils {
    fun loadImageRoundedCorners(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(45, 0,
                        RoundedCornersTransformation.CornerType.ALL)))
                .into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(RequestOptions().fitCenter()).into(imageView)
    }

    /*
        当fragment或者activity失去焦点或者destroyed的时候，Glide会自动停止加载相关资源，确保资源不会被浪费
     */
    fun loadUrlImage(context: Context, url: String, imageView: ImageView){

        var ops=RequestOptions()
                .placeholder(R.drawable.glide_placeholder)
                .error(R.drawable.glide_error)
                .circleCrop()

        Glide.with(context).load(url).apply(ops).into(object: SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageView.setImageDrawable(resource)
            }
        })
    }

}
