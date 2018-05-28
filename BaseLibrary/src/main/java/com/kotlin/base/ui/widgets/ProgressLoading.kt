package com.kotlin.base.ui.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import com.kotlin.base.R
import com.orhanobut.logger.Logger
import org.jetbrains.anko.find

/**
 * Created by  on 2018/5/9.
 */
class ProgressLoadingBar  constructor(context: Context?, themeResId: Int) : Dialog(context, themeResId) {

    lateinit var mAnimationDrawable: AnimationDrawable

    init {
        initView()
    }

    private fun initView() {
        this.setContentView(R.layout.progress_dialog)

        mAnimationDrawable=this.find<ImageView>(R.id.iv_loading_image).background as AnimationDrawable

        val lp = this.window.attributes
        lp.dimAmount = 0.2f

        lp.gravity= Gravity.CENTER
        //设置属性
        this.window.attributes = lp
        Logger.e("对话框-------。initView")
    }






    fun showLoadingBar(){


        this.show()
        mAnimationDrawable?.start()
    }

    fun dismissLoadingBar(){

        this.dismiss()

        mAnimationDrawable?.stop()
    }

}