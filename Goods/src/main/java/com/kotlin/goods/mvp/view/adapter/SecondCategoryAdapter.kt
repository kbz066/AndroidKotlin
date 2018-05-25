package com.kotlin.goods.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.ext.loadImageFitCenter
import com.kotlin.goods.R
import com.kotlin.goods.mvp.model.response.CategoryResPonse
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * Created by  on 2018/5/25.
 */
class SecondCategoryAdapter(layoutId:Int= R.layout.layout_second_category_item) : BaseQuickAdapter<CategoryResPonse, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: CategoryResPonse) {

        helper.itemView.iv_category_icon.loadImageFitCenter(item.categoryIcon)
        helper.itemView.tv_second_category_name.text=item.categoryName
    }
}