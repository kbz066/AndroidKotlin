package com.kotlin.user.mvp.ui.activity

import android.os.Bundle
import com.kotlin.base.Rx.BaseRxResponse
import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.R.id.test
import com.kotlin.user.mvp.model.UserApi
import com.kotlin.user.mvp.model.UserRegisterRequest
import com.kotlin.user.mvp.presenter.RigisterPresenter
import com.kotlin.user.mvp.presenter.view.RigisterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rigister.*
import kotlin.concurrent.thread

class RigisterActivity : BaseMvpActivity<RigisterPresenter>(),RigisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rigister)


        mpresenter= RigisterPresenter();
        mpresenter.mView=this;
        //mpresenter.register("","","")

        test.setOnClickListener {
            runOnUiThread {
                RetrofitManager.mInstance.create(UserApi::class.java)
                        .register(UserRegisterRequest("0","1","123456"))
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
    }

}
