package com.kotlin.base.ext

import android.support.design.widget.FloatingActionButton
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.ui.widgets.DefaultTextWatcher
import com.kotlin.base.utils.GlideUtils
import com.trello.rxlifecycle2.LifecycleProvider
import com.vondear.rxtools.RxNetTool
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



/**
 * Created by  on 2018/5/4.
 */

 fun <T : BaseResponse<*>> Observable<T>.excute(checkNet:()->Boolean,subscribe: BaseRxObserver<T>,rxLifecycle: LifecycleProvider<*>){

         this.compose(rxLifecycle.bindToLifecycle())
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .doOnSubscribe{

                     if (checkNet.invoke().not()){
                         it.dispose()
                     }
                 }



                 .subscribe(subscribe);
}

fun Button.enable(vararg editText: EditText,isBtnEnable:()->Boolean){


    var btn=this


    editText.forEach {
        it.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                btn.isEnabled=isBtnEnable()

            }

        })
    }

}


fun ImageView.loadImageRoundedCorners(url:String){
    GlideUtils.loadImageRoundedCorners(this.context,url,this)
}

fun ImageView.loadImageFitCenter(url:String){
    GlideUtils.loadImageFitCenter(this.context,url,this)
}

fun ImageView.loadUrlImage(url:String){
    GlideUtils.loadUrlImage(this.context,url,this)
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}