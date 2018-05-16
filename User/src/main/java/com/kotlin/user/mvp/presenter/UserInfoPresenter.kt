package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.mvp.model.response.UserInfoResponse
import com.kotlin.user.mvp.model.server.UploadImageServer
import com.kotlin.user.mvp.model.server.UserServer
import com.kotlin.user.mvp.presenter.view.ResetPwdView
import com.kotlin.user.mvp.presenter.view.UserInfoView
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UserInfoPresenter @Inject constructor():BasePresenter<UserInfoView>() {



    @Inject
    lateinit var mUserServer: UserServer



    @Inject
    lateinit var mUploadImageServer: UploadImageServer



    fun updateUserInfo( userIcon:String,userName:String,userGender:String,userSign:String){


        mUserServer.editUser(userIcon,userName,userGender,userSign)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<UserInfoResponse>>(mView){
                    override fun success(data: BaseResponse<UserInfoResponse>) {
                        mView.onEditUserResult(data.data)

                    }

                    override fun failure(statusCode: Int, msg: String?) {
                        mView.onError(statusCode,msg)
                    }

                },rxLifecycle)



    }

    fun uploadImage( oosPath:String, locationPath:String){
        mUploadImageServer.uploadHeaderIcon(mContext,ProviderConstant.OOS_BUCKET_NAME,oosPath,locationPath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {


                            mView.onUploadImageResult(true)

                        },
                        {

                            mView.onUploadImageResult(false)

                        }
                )

    }


}