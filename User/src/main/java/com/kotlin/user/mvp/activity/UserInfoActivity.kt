package com.kotlin.user.mvp.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.format.DateUtils
import com.alibaba.fastjson.JSON
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.R
import com.kotlin.user.R.id.tb_user_info_bar
import com.kotlin.user.dagger.component.DaggerUserComponent
import com.kotlin.user.dagger.module.UserModule
import com.kotlin.user.mvp.model.response.UserInfoResponse
import com.kotlin.user.mvp.presenter.UserInfoPresenter
import com.kotlin.user.mvp.presenter.view.UserInfoView
import com.vondear.rxtools.RxSPTool
import kotlinx.android.synthetic.main.activity_user_info.*
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager

import org.jetbrains.anko.toast
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.kotlin.base.utils.GlideUtils
import com.kotlin.user.R.id.iv_user_icon_image
import com.vondear.rxtools.RxFileTool
import com.vondear.rxtools.RxTimeTool
import java.io.File


class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>() ,UserInfoView, TakePhoto.TakeResultListener {
    override fun onGetUploadTokenResult(result: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEditUserResult(result: UserInfoResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val mTakePhoto:TakePhoto by lazy { TakePhotoImpl(this,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        mTakePhoto.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        mTakePhoto.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }




    override fun getContentViewResId(): Int {
        return R.layout.activity_user_info
    }


    override fun initView() {

        setSupportActionBar(tb_user_info_bar.getToolBar())
        tb_user_info_bar.getRightView().setOnClickListener {

        }

        iv_user_icon_image.setOnClickListener {

            showSelectDialog();
        }

        checkRxPermissions( Manifest.permission.CAMERA,mPermissionsListener = object :PermissionsListener{
            override fun onPermissionsFail() {

            }

            override fun onPermissionsSuccess() {

            }

        })
        initData()
    }

    /**
     * 选择图片对话框
     */
    private fun showSelectDialog() {


        var listener= OnItemClickListener {
            view, position ->

            when(position){

                0->{
                    mTakePhoto.onPickFromCapture(getImageCropUri())
                }


                1->{


                }
            }

        }


        //builder模式创建
        AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择图片")
                .setMessage(null)
                .setCancelText("取消")
                .setOthers(arrayOf("拍照", "从相册中选择"))
                .setOnItemClickListener(listener)
                .build()
                .show()

    }




    /**
     * 初始化数据
     */
    private fun initData() {

        var json:String?=RxSPTool.readJSONCache(this, ProviderConstant.KEY_SP_USER_CACHE)
        json?.let {
            var info:UserInfoResponse=JSON.parseObject(json,UserInfoResponse::class.java)
            et_user_name.setText(info.userName)
            et_user_sign.setText(info.userSign)
            tv_user_mobile.setText(info.userMobile)
        }

    }

    override fun injectComponent() {

        DaggerUserComponent.builder().baseActivityComponent(mActiviComponent)
                .userModule(UserModule())
                .build().inject(this)
    }





    override fun takeSuccess(result: TResult?) {
        toast("图片路径-------》"+result!!.getImage().originalPath)
        GlideUtils.loadUrlImage(this,result!!.getImage().originalPath, iv_user_icon_image)
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {

    }


    //获得照片的输出保存Uri
    private fun getImageCropUri(): Uri {
        val file = File(RxFileTool.getSDCardPath(), "temp/" + System.currentTimeMillis() + ".jpg")
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }

        return Uri.fromFile(file)
    }
}
