package com.kotlin.base.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.text.AutoText
import android.util.AttributeSet
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.kotlin.base.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by  on 2018/5/9.
 */
class VerificationButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {


    var defaultTxtColor:Int=0
    var showTxtNumber:Long=59


    init {
        this.setText("获取验证码")

        defaultTxtColor=textColors.defaultColor
    }




    //倒计时
    fun afterMeterTime(){

        //设置状态


        this.isEnabled=false
        this.setTextColor(resources.getColor(R.color.common_yellow))


        Observable.interval(0,1,TimeUnit.SECONDS)
                .take(60)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Long>{
                    override fun onComplete() {

                        resetCounter()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Long) {
                        showTxtNumber-=1
                        this@VerificationButton.setText(showTxtNumber.toString()+"S")

                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }





    /**
     * 重置状态
     */
    fun resetCounter(){

        this.isEnabled=true
        this.setText("重获验证码")
        this.showTxtNumber=59
        this.setTextColor(defaultTxtColor)

    }

}