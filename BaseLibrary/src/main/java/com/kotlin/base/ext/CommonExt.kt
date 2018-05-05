package com.kotlin.base.ext

import com.kotlin.base.Rx.BaseRxObserver
import com.kotlin.base.data.protocol.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



/**
 * Created by caofu on 2018/5/4.
 */

 fun <T : BaseResponse<*>> Observable<T>.excute(subscribe: BaseRxObserver<T>){

         this
             .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(subscribe);
}
