package com.kotlin.goods.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.goods.mvp.model.response.CartGoodsResponse
import com.kotlin.goods.mvp.model.server.CartService
import com.kotlin.goods.mvp.presenter.view.ICartView
import com.orhanobut.logger.Logger
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


    fun deleteCart(list: List<Int>){
        mCartService.deleteCartList(list).excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<String>>(mView) {
            override fun success(data: BaseResponse<String>) {
                if (data.data!=null){
                    mView.onDeleteCartListResult(data.data)
                }else{
                    mView.onDeleteCartListResult("")

                }

            }

            override fun failure(statusCode: Int, msg: String?) {

            }


        },rxLifecycle)
    }

    fun submitCart(list: MutableList<CartGoodsResponse>, totalPrice: Long){
        mCartService.submitCart(list,totalPrice).excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<Int>>(mView) {
            override fun success(data: BaseResponse<Int>) {

                mView.onSubmitCartListResult(data.data)


            }

            override fun failure(statusCode: Int, msg: String?) {

            }


        },rxLifecycle)
    }

}