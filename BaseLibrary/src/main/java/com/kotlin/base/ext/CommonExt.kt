package com.kotlin.base.ext

import android.support.design.widget.FloatingActionButton
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import com.kotlin.base.ui.widgets.DefaultTextWatcher
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



/**
 * Created by  on 2018/5/4.
 */

 fun <T : BaseResponse<*>> Observable<T>.excute(subscribe: BaseRxObserver<T>,rxLifecycle: LifecycleProvider<*>){

         this.subscribeOn(Schedulers.newThread())
                 .compose(rxLifecycle.bindToLifecycle())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(subscribe);
}

fun Button.enable(vararg editText: EditText,isBtnEnable:()->Boolean){


    var btn=this


    editText.forEach {
        it.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                println("isBtnEnable--------------->"+isBtnEnable())
                btn.isEnabled=isBtnEnable()
                println("isBtnEnable---2------------>"+btn.isEnabled)
            }

        })
    }
}
