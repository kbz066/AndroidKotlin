package com.kotlin.goods.mvp.view.fragment



import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kotlin.base.ui.fragment.BaseMvpFragment

import com.kotlin.goods.R
import com.kotlin.goods.R.id.*
import com.kotlin.goods.adapter.SecondCategoryAdapter
import com.kotlin.goods.adapter.TopCategoryAdapter
import com.kotlin.goods.dagger.component.DaggerCategoryComponent
import com.kotlin.goods.dagger.module.CategoryModule
import com.kotlin.goods.mvp.model.response.CategoryResPonse
import com.kotlin.goods.mvp.presenter.CategoryPresenter
import com.kotlin.goods.mvp.presenter.view.ICategoryView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category_context.view.*
import org.jetbrains.anko.find

class CategoryFragment : BaseMvpFragment<CategoryPresenter>() ,ICategoryView{


    private lateinit var mTopAdapter: TopCategoryAdapter
    private lateinit var mSecondCategoryAdapter: SecondCategoryAdapter


    override fun injectComponent() {
        DaggerCategoryComponent.builder().baseActivityComponent(mActiviComponent)
                .categoryModule(CategoryModule())
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {
        return R.layout.fragment_category
    }


    override fun initView() {
        tb_category_bar.hideCloseBar()


        initTopCategoryList()
        initSecondCategoryList()
        getCategoryList()

    }



    private fun initTopCategoryList() {
        mTopAdapter=TopCategoryAdapter()
        rv_top_category_list.layoutManager=LinearLayoutManager(activity)
        rv_top_category_list.adapter=mTopAdapter
        mTopAdapter.setOnItemClickListener {
            adapter, view, position ->

            mTopAdapter.data.forEach {
                it.isSelected=false
            }
            mTopAdapter.data[position].isSelected=true
            mTopAdapter.notifyDataSetChanged()


            getCategoryList(mTopAdapter.data.get(position).id)
        }

    }
    private fun initSecondCategoryList() {
        mSecondCategoryAdapter=SecondCategoryAdapter()
        var rv_second_category_list=mv_multiple_status_view.contentView.rv_second_category_list;

        rv_second_category_list.layoutManager=GridLayoutManager(activity,3);
        rv_second_category_list.adapter=mSecondCategoryAdapter
    }

    /**
     * 获取商品分类列表
     */
    fun getCategoryList(id:Int=0){

        if (id!=0){
            mv_multiple_status_view.showLoading()
        }
        mPresenter.getCategory(id)
    }


    /**
     * 获取商品列表回调
     */
    override fun onGetCategoryResult(result: MutableList<CategoryResPonse>?) {



        if (result!=null&&result.size>0){



            if (result.get(0).parentId==0){
                result.get(0).isSelected=true
                getCategoryList(result.get(0).id)
                mTopAdapter.setNewData(result)


            }else{

                mv_multiple_status_view.showContent()
                mSecondCategoryAdapter.setNewData(result)
            }
        }else {
            mv_multiple_status_view.showEmpty()
        }

    }

}
