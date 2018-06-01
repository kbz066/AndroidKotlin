package com.kotlin.goods.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.model.server.GoodsListServer
import com.kotlin.goods.mvp.presenter.view.IGoodsListView
import javax.inject.Inject

/**
 * Created by  on 2018/5/24.
 */
class GoodsListPresenter @Inject constructor() :  BasePresenter<IGoodsListView>() {


    @Inject
    lateinit var mCategoryServer: GoodsListServer



    fun getGoodsList(categoryId: Int, pageNo: Int){

        mCategoryServer.getGoodsList(categoryId,pageNo)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<GoodsListResponse>?>>(mView) {
                    override fun success(data: BaseResponse<MutableList<GoodsListResponse>?>) {
                        mView.onGetGoodsListResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }

    fun getGoodsListByKeyword( keyword: String, pageNo: Int){

        mCategoryServer.getGoodsListByKeyword(keyword,pageNo)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<GoodsListResponse>?>>(mView) {
                    override fun success(data: BaseResponse<MutableList<GoodsListResponse>?>) {
                        mView.onGetGoodsListResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                    }


                },rxLifecycle)
    }

}