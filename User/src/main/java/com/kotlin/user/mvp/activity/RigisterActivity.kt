package com.kotlin.user.mvp.activity

import android.os.Bundle
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R



import com.kotlin.user.mvp.presenter.RigisterPresenter
import com.kotlin.user.mvp.presenter.view.RigisterView
import kotlinx.android.synthetic.main.activity_rigister.*
import org.jetbrains.anko.toast


class RigisterActivity : BaseMvpActivity<RigisterPresenter>(),RigisterView {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rigister)


        mpresenter= RigisterPresenter();
        mpresenter.mView=this;

//
//        test_txt.setOnClickListener {
//
//
//            mpresenter.register("","","")
//        }
    }
    override fun onRegisterSuccess(result: String) {

        toast("注册成功")
    }

    override fun onRegisterFailure(statusCode: Int, msg: String?) {
        toast("注册失败(${msg})")
    }
}
