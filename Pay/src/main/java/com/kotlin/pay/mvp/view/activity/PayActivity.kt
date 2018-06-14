package com.kotlin.pay.mvp.view.activity

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.pay.R
import com.kotlin.pay.dagger.component.DaggerPayComponent
import com.kotlin.pay.mvp.presenter.PayPresenter
import com.kotlin.pay.mvp.presenter.view.IPayView
import com.kotlin.provider.common.ARouterPath
import com.kotlin.provider.common.ProviderConstant
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import com.alipay.sdk.app.PayTask
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_pay.*
import org.jetbrains.anko.uiThread


@Route(path = ARouterPath.PATH_PAY)
class PayActivity : BaseMvpActivity<PayPresenter>() ,IPayView{




    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var orderId=0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var price=0L


    override fun injectComponent() {

        DaggerPayComponent.builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this

    }

    override fun getContentViewResId(): Int {

        return R.layout.activity_pay
    }

    override fun initView() {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//支付宝沙箱环境
        btn_pay.setOnClickListener {




            mPresenter.getPaySign(orderId,price)
        }

    }




    override fun onGetSignResult(result: String) {

        doAsync {
            val alipay = PayTask(this@PayActivity)

            val result = alipay.payV2(result, true)


            if (result["resultStatus"].equals("9000")){


            }else{
               uiThread {
                   toast("支付失败${result["memo"]}")
               }
            }

        }

    }

    override fun onPayOrderResult() {

    }


}
