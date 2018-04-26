package com.kotlin.user.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.presenter.RigisterPresenter
import com.kotlin.user.presenter.view.RigisterView

import kotlinx.android.synthetic.main.activity_rigister.*

class RigisterActivity : BaseMvpActivity<RigisterPresenter>(),RigisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rigister)


        mpresenter= RigisterPresenter();
        mpresenter.mView=this;
        mpresenter.test()

    }

}
