package com.kotlin.base.ui.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.kotlin.base.R
import com.kotlin.base.common.AppManager
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.components.RxActivity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.vondear.rxtools.RxActivityTool
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.toast
import java.lang.reflect.Array.setInt
import javax.inject.Inject


open class BaseActivity : RxAppCompatActivity(){



    private val mRxPermissions: RxPermissions by lazy {  RxPermissions(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RxActivityTool.addActivity(this)
    }

    fun checkRxPermissions(vararg permissions: String,mPermissionsListener:PermissionsListener) {


        mRxPermissions.request(*permissions)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({






                    if (it){
                        toast("权限申请成功")
                        mPermissionsListener?.onPermissionsSuccess()
                    }else{
                        mPermissionsListener?.onPermissionsFail()
                        toast("未授权权限，部分功能不能使用")
                    }
                })

    }


    /**
     * 权限申请回调
     */
    interface PermissionsListener{
        fun onPermissionsSuccess()
        fun onPermissionsFail()
    }


    override fun onDestroy() {

        super.onDestroy()
        RxActivityTool.finishActivity(this)
    }



}
