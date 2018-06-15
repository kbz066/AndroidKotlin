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
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.utils.YuanFenConverter.changeF2YWithUnit
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
        tv_alipay_type.setOnClickListener {
            updateButState(true,false,false)
        }
        tv_weixin_type.setOnClickListener {
            updateButState(false,true,false)

        }
        tv_bankcard_type.setOnClickListener {
            updateButState(false,false,true)

        }


        btn_pay.setOnClickListener {
            mPresenter.getPaySign(orderId,price)
        }

        Logger.e("initView------------")
        tv_alipay_type.isSelected=true
        tv_total_price.text="待支付：${YuanFenConverter.changeF2YWithUnit(price)}"
    }
    fun updateButState(isAliPay:Boolean,isWeixinPay:Boolean,isBankCardPay:Boolean){
        tv_alipay_type.isSelected=isAliPay

        tv_weixin_type.isSelected=isWeixinPay

        tv_bankcard_type.isSelected=isBankCardPay
    }




    override fun onGetSignResult(result: String) {

        doAsync {
            val alipay = PayTask(this@PayActivity)

            val result = alipay.payV2(result, true)

            uiThread {
                if (result["resultStatus"].equals("9000")){

                    mPresenter.payOrder(orderId)

                }else{
                    toast("支付失败${result["memo"]}")
                }
            }


        }

    }

    override fun onPayOrderResult() {
        toast("支付成功")
        finish()
    }


}
