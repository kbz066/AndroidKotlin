package com.kotlin.goods.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.model.server.CartService
import com.kotlin.goods.mvp.model.server.GoodsListServer
import com.kotlin.goods.mvp.presenter.view.IGoodsDetailView
import com.kotlin.goods.mvp.presenter.view.IGoodsListView
import javax.inject.Inject

/**
 * Created by  on 2018/5/24.
 */
class GoodsDetailPresenter @Inject constructor() :  BasePresenter<IGoodsDetailView>() {


    @Inject
    lateinit var mCategoryServer: GoodsListServer

    @Inject
    lateinit var mCartService: CartService

    fun getGoodsDetail(goodsId: Int){

        mCategoryServer.getGoodsDetail(goodsId)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<GoodsListResponse>>(mView) {
                    override fun success(data: BaseResponse<GoodsListResponse>) {
                        mView.onGetGoodsDetailResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }


    /**
     * 添加购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String){
        mCartService.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice, goodsCount,goodsSku)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<Int>>(mView) {
                    override fun success(data: BaseResponse<Int>) {
                        mView.onAddCartResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }

}