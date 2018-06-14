package com.kotlin.base.ui.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.kotlin.base.R
import com.kotlin.base.common.AppManager
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.components.RxActivity
import com.trello.rxlifecycle2.components.RxFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.toast
import java.lang.reflect.Array.setInt
import javax.inject.Inject


abstract class BaseFragment : RxFragment(){



    private val mRxPermissions: RxPermissions by lazy {  RxPermissions(this.activity)}


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater!!.inflate(getContentViewResId(),null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

    }

    fun requestRxPermissions(vararg permissions: String,mPermissionsListener:PermissionsListener) {


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
     * 子类提供ContentView
     */
    abstract fun getContentViewResId():  Int

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 权限申请回调
     */
    interface PermissionsListener{
        fun onPermissionsSuccess()
        fun onPermissionsFail()
    }





}
