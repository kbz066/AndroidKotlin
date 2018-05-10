package com.kotlin.base.ui.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.kotlin.base.R
import com.kotlin.base.common.AppManager
import com.trello.rxlifecycle2.components.RxActivity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.vondear.rxtools.RxActivityTool
import java.lang.reflect.Array.setInt
import javax.inject.Inject


open class BaseActivity : RxAppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RxActivityTool.addActivity(this)
    }

    override fun onDestroy() {

        super.onDestroy()
        RxActivityTool.finishActivity(this)
    }


}