package com.kotlin.goods.mvp.view.fragment



import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kotlin.base.ui.fragment.BaseMvpFragment

import com.kotlin.goods.R
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.mvp.view.adapter.SecondCategoryAdapter
import com.kotlin.goods.mvp.view.adapter.TopCategoryAdapter
import com.kotlin.goods.dagger.component.DaggerCategoryComponent
import com.kotlin.goods.dagger.module.CategoryModule
import com.kotlin.goods.mvp.model.response.CategoryResponse
import com.kotlin.goods.mvp.presenter.CategoryPresenter
import com.kotlin.goods.mvp.presenter.view.ICategoryView
import com.kotlin.goods.mvp.view.activity.GoodsListActivity
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category_content.view.*
import org.jetbrains.anko.startActivity

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
        //点击事件
        mSecondCategoryAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position ->

            startActivity<GoodsListActivity>( GoodsConstant.KEY_GOODS_ID to mSecondCategoryAdapter.data[position].id)
        }
    }

    /**
     * 获取商品分类列表
     */
    fun getCategoryList(id:Int=0){


        println("aaaaaaaaaaaaaa        "+id)
        if (id!=0){
            mv_multiple_status_view.showLoading()
        }
        mPresenter.getCategory(id)
    }


    /**
     * 获取商品列表回调
     */
    override fun onGetCategoryResult(result: MutableList<CategoryResponse>?) {



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
