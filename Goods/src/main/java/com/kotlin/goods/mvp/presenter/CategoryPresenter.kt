package com.kotlin.goods.mvp.presenter

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.net.RetrofitManager
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ext.excute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.goods.mvp.model.CategoryApi
import com.kotlin.goods.mvp.model.request.GetCategoryRequest
import com.kotlin.goods.mvp.model.response.CategoryResPonse
import com.kotlin.goods.mvp.presenter.view.ICategoryView
import com.kotlin.user.mvp.model.server.CategoryServer
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by  on 2018/5/24.
 */
class CategoryPresenter @Inject constructor() :  BasePresenter<ICategoryView>() {


    @Inject
    lateinit var mCategoryServer: CategoryServer



    fun getCategory(parentId: Int){

        mCategoryServer.getCategory(parentId)
                .excute({checkNetWork(mView)},object : BaseRxObserver<BaseResponse<MutableList<CategoryResPonse>?>>(mView) {
                    override fun success(data: BaseResponse<MutableList<CategoryResPonse>?>) {
                        mView.onGetCategoryResult(data.data)
                    }

                    override fun failure(statusCode: Int, msg: String?) {

                        Logger.e("getCategory failure"+msg)
                    }


                },rxLifecycle)
    }

}