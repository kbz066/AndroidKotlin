package com.kotlin.base.ui.widgets

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.kotlin.base.R
import android.util.TypedValue
import android.widget.TextView
import kotlinx.android.synthetic.main.layout_search_history_view.view.*
import android.support.v7.widget.SearchView.SearchAutoComplete
import android.view.View
import com.alibaba.fastjson.JSON
import com.kotlin.base.common.BaseConstant
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.adapter.SearchHistoryAdapter
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxSPTool


/**
 * Created by  on 2018/5/28.
 */
class SearchHistoryView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) ,View.OnClickListener{



    private var searchFun:((key:String)->Unit) ? = null
    private var view:View?=null
    private lateinit var mHistoryAdapter: SearchHistoryAdapter


    init {
        view=LayoutInflater.from(context).inflate(R.layout.layout_search_history_view,this,true)

        initHistoryRecyclerView()
        view!!.bt_clear_history.setOnClickListener(this)
        view!!.rv_search_txt.setOnClickListener(this)
        view!!.iv_left_back.setOnClickListener(this)
    }

    private fun initHistoryRecyclerView() {
        mHistoryAdapter=SearchHistoryAdapter()
        view!!.rv_history_list.layoutManager=LinearLayoutManager(context)
        view!!.rv_history_list.adapter=mHistoryAdapter
        mHistoryAdapter.setOnItemClickListener {
            adapter, view, position ->

            this.view!!.et_search_txt.setText(mHistoryAdapter.data[position])
        }
        loadHistoryData()
    }

    fun  setNewHistoryData(datas:List<String>){

        mHistoryAdapter.setNewData(datas)
    }

    fun  addHistoryData(data:String){

        mHistoryAdapter.addData(data)
    }

    fun saveHistoryData(){
        RxSPTool.putJSONCache(context,BaseConstant.KEY_SP_SEARCH_HISTORY,JSON.toJSONString(mHistoryAdapter.data))
    }

    fun loadHistoryData(){
        var json=RxSPTool.readJSONCache(context,BaseConstant.KEY_SP_SEARCH_HISTORY)
        json?.let {
            var list=JSON.parseArray(it,String::class.java)

            list?.let {
                mHistoryAdapter.setNewData(it)
            }
        }

    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.bt_clear_history->{
                RxSPTool.clearPreference(context,BaseConstant.SP_JSON_NAME,BaseConstant.KEY_SP_SEARCH_HISTORY)
                mHistoryAdapter.setNewData(null)
            }

            R.id.rv_search_txt->{
                searchFun?.let {
                    it.invoke(view!!.et_search_txt.text.toString())
                    addHistoryData(view!!.et_search_txt.text.toString())
                }

            }
            R.id.iv_left_back->{

                var activity=context as BaseActivity
                activity.finish()
            }
        }
    }
    fun getSearchTxt():String{
        return et_search_txt.text.toString()
    }
    fun setSearchFun(searchFun:(key:String)->Unit){
        this.searchFun=searchFun
    }
}

