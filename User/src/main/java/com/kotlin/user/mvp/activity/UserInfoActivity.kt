package com.kotlin.user.mvp.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.text.format.DateUtils
import com.alibaba.fastjson.JSON
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.R
import com.kotlin.user.dagger.component.DaggerUserComponent
import com.kotlin.user.dagger.module.UserModule
import com.kotlin.user.mvp.model.response.UserInfoResponse
import com.kotlin.user.mvp.presenter.UserInfoPresenter
import com.kotlin.user.mvp.presenter.view.UserInfoView

import kotlinx.android.synthetic.main.activity_user_info.*
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager

import org.jetbrains.anko.toast
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.kotlin.base.ui.activity.BaseTakePhotoActivity
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.utils.OSSUtils
import com.kotlin.user.R.id.*
import com.kotlin.user.utils.UserInfoUtils
import com.orhanobut.logger.Logger

import io.reactivex.Observable
import java.io.File
import java.util.*


class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>() ,UserInfoView, TakePhoto.TakeResultListener {



    //上传图片成功的网络oss地址
    private var ossImagePath:String?=null
    //选择的图片名字
    private var locationFileName:String?=null





    override fun getContentViewResId(): Int {
        return R.layout.activity_user_info
    }


    override fun initView() {

        setSupportActionBar(tb_user_info_bar.getToolBar())

        //保存
        tb_user_info_bar.getRightView().setOnClickListener {

            mPresenter.updateUserInfo(
                    if (ossImagePath==null) "" else ossImagePath!!,

                    et_user_name.text.toString(),
                    if (rb_gendermale.isChecked) "0" else "1",
                    et_user_sign.text.toString()
            )
        }


        iv_user_icon_image.setOnClickListener {

            showSelectDialog();
        }

        //申请权限
        requestRxPermissions( Manifest.permission.CAMERA,mPermissionsListener = object :PermissionsListener{
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
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this, AlertView.Style.ActionSheet, OnItemClickListener {
            o, position ->

            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
            when (position) {
                0 -> {

                    createTempFile()
                    mTakePhoto.onPickFromCapture(ProviderConstant.getImageCropUri())
                }

                1 -> {
                    mTakePhoto.onPickFromGallery()
                }

            }
        }

        ).show()

    }




    /**
     * 初始化数据
     */
    private fun initData() {

        UserInfoUtils.getUserInfo()?.let {

            et_user_name.setText(it.userName)
            tv_user_mobile.setText(it.userMobile)


            if (TextUtils.isEmpty(it.userSign).not()){
                et_user_sign.setText(it.userSign)
            }
            if (TextUtils.isEmpty(it.userIcon).not()){

                ossImagePath=it.userIcon
                GlideUtils.loadUrlImage(this,it.userIcon,iv_user_icon_image)
            }
            if (it.userGender == "0") {
                rb_gendermale.isChecked = true
            }
            else {
                rb_genderfemale.isChecked = true
            }
        }

    }

    override fun injectComponent() {

        DaggerUserComponent.builder().baseActivityComponent(mActiviComponent)
                .userModule(UserModule())
                .build().inject(this)
        mPresenter.mView=this
    }


    /**
     * 选择图片成功
     */
    override fun takeSuccess(result: TResult?) {
        toast("图片路径-------》"+result!!.getImage().compressPath)

        locationFileName=getImageFileName(result);
        mPresenter.uploadImage("img/${locationFileName}",result.image.compressPath)



    }



    /**
     * 上传头像结果
     */
    override fun onUploadImageResult(flag: Boolean) {
        if (flag){

            ossImagePath=getOssPhotoHttpUrl()
            GlideUtils.loadUrlImage(this,ossImagePath!!,iv_user_icon_image)
        }else{
            toast("上传失败,请重试")
        }
    }

    /**
     * 修改个人信息成功回调
     */
    override fun onEditUserResult(result: UserInfoResponse) {
        toast("修改成功")
        EventBusUtils.post(result)
    }

    /**
     * 修改个人信息失败
     */
    override fun onError(statusCode: Int, msg: String?) {
        toast("${msg}")
    }





    /**
     * 获取选择的图片名字
     */
    private fun getImageFileName(result: TResult?):String{

        var path=result!!.getImage().compressPath;
        var index=path.lastIndexOf("/")
        return  path.substring(index+1,path.length)
    }

    /**
     *
     * @return 图片上传到阿里云的路径拼接
     */
        fun getOssPhotoHttpUrl(): String {

            return StringBuilder()
                    .append("http://")
                    .append(ProviderConstant.OOS_BUCKET_NAME)
                    .append(".")
                    .append(ProviderConstant.OOS_ENDPOINT)
                    .append("/img/")
                    .append(locationFileName)
                    .toString()
        }
}
