package com.kotlin.message.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.goods.dagger.component.DaggerMessageComponent
import com.kotlin.goods.mvp.presenter.MessagePresenter
import com.kotlin.goods.mvp.presenter.view.IMessageView

import com.kotlin.message.R
import com.kotlin.message.mvp.model.response.MessageResponse
import com.kotlin.message.mvp.view.adapter.MessageAdapter
import com.kotlin.provider.event.UpdateMessageBadgeEvent
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_message_content.*


class MessageFragment : BaseMvpFragment<MessagePresenter>() , IMessageView {

    private lateinit var mMessageAdapter: MessageAdapter

    override fun injectComponent() {
        DaggerMessageComponent.builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)
        mPresenter.mView=this
    }

    override fun getContentViewResId(): Int {

        return R.layout.fragment_message
    }

    override fun onStart() {
        mv_multi_state_View.showLoading()
        mPresenter.getMessageList()
        super.onStart()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden.not()){
            EventBusUtils.post(UpdateMessageBadgeEvent(false))

        }
        super.onHiddenChanged(hidden)
    }

    override fun initView() {

        mMessageAdapter=MessageAdapter()
        rv_message_list.layoutManager=LinearLayoutManager(activity)
        rv_message_list.adapter=mMessageAdapter
    }

    override fun onGetMessageResult(result: MutableList<MessageResponse>?) {

        if (result!=null&&result.size>0){


            mMessageAdapter.setNewData(result)
            mv_multi_state_View.showContent()
        }else {
            mv_multi_state_View.showEmpty()
        }
    }




}
