package com.kotlin.base.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.R

import kotlinx.android.synthetic.main.layout_search_history_item.view.*

/**
 * Created by  on 2018/5/28.
 */
class SearchHistoryAdapter(layoutId:Int= R.layout.layout_search_history_item): BaseQuickAdapter<String, BaseViewHolder>(layoutId) {


    override fun convert(helper: BaseViewHolder, item: String?) {
        helper.itemView.tv_search_history_txt.text=item
    }
}