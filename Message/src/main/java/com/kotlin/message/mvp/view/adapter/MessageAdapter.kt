package com.kotlin.message.mvp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.base.ext.loadUrlImage
import com.kotlin.message.R
import com.kotlin.message.mvp.model.response.MessageResponse
import kotlinx.android.synthetic.main.layout_message_item.view.*

/**
 * Created by  on 2018/6/15.
 */
class MessageAdapter(layoutId:Int= R.layout.layout_message_item) : BaseQuickAdapter<MessageResponse, BaseViewHolder>(layoutId) {
    override fun convert(helper: BaseViewHolder, item: MessageResponse) {
        //消息图标
        helper.itemView.iv_msg_icon.loadUrlImage(item.msgIcon)
        //消息标题
        helper.itemView.tv_msg_title.text = item.msgTitle
        //消息内容
        helper.itemView.tv_msg_content.text = item.msgContent
        //消息时间
        helper.itemView.tv_msg_time.text = item.msgTime
    }
}