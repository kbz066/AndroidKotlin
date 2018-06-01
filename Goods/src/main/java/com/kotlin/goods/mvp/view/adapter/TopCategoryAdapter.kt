package com.kotlin.goods.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.goods.R
import com.kotlin.goods.mvp.model.response.CategoryResponse
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * Created by  on 2018/5/24.
 */
class TopCategoryAdapter(layoutResId: Int= R.layout.layout_top_category_item) :BaseQuickAdapter<CategoryResponse,BaseViewHolder>(layoutResId){



    override fun convert(helper: BaseViewHolder, item: CategoryResponse) {


        helper.itemView.tv_top_category_name.text = item.categoryName
        helper.itemView.tv_top_category_name.isSelected = item.isSelected

    }
}