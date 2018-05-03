package com.kotlin.user.mvp.presenter

import com.kotlin.base.Rx.BaseRxResponse
import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.user.mvp.model.UserApi
import com.kotlin.user.mvp.model.UserRegisterRequest
import com.kotlin.user.mvp.presenter.view.RigisterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Body
import rx.Observer


class RigisterPresenter:BasePresenter<RigisterView>() {



    fun test(){

        println("mvp配置完成")
    }
    fun register( email:String, pwd:String, code:String){
        RetrofitManager.mInstance.create(UserApi::class.java)
                .register(UserRegisterRequest(email,pwd,code))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseRxResponse<BaseResponse<String>>(){
                    override fun success(data: BaseResponse<String>) {

                        println("注册 用户----成功------------》"+data.code+"\t\t\t\t\t"+data.data)
                    }

                    override fun failure(statusCode: Int, htttpErrorType: HtttpErrorType) {
                        println("注册 用户----失败------------》"+statusCode+"\t\t\t\t"+htttpErrorType.msg)
                    }


                })
    }

}