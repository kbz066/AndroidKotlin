package com.kotlin.base.ui.widgets

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Created by  on 2018/5/19.
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadImageFitCenter(context,path.toString(),imageView)
    }
}