package com.kotlin.goods.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import com.kotlin.goods.mvp.model.server.CartService
import com.kotlin.goods.mvp.presenter.view.ICartView
import javax.inject.Inject

/**
 * Created by  on 2018/6/4.
 */
class CartPresenter @Inject constructor() :  BasePresenter<ICartView>() {

    @Inject
    lateinit var mCartService: CartService

    fun getCartList(){
        mCartService.getCartList().excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<CartGoodsResponse>?>>(mView) {
            override fun success(data: BaseResponse<MutableList<CartGoodsResponse>?>) {
                mView.onGetCartListResult(data.data)
            }

            override fun failure(statusCode: Int, msg: String?) {

            }


        },rxLifecycle)
    }

}