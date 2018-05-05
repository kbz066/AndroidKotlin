package com.kotlin.base.Rx

import com.alibaba.fastjson.JSONException
import com.kotlin.base.data.protocol.BaseResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException


import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by  on 2018/5/2.
 */
abstract class  BaseRxObserver<T : BaseResponse<*>>: Observer<T> {


    abstract fun success(data: T)
    abstract fun failure(statusCode: Int, msg: String?)


    enum class HtttpErrorType(val code: Int,val msg:String) {

        //根据实际情况进行增删
        INTERNAL_SERVER_ERROR(500, "（服务器内部错误） 服务器遇到错误，无法完成请求" ),
        BAD_GATEWAY(502,"（错误网关） 服务器作为网关或代理，从上游服务器收到无效响应"),
        NOT_FOUND(404, "未找到。服务器找不到请求的网页"),
        CONNECTION_TIMEOUT(408, "（请求超时） 服务器等候请求时发生超时"),
        NETWORK_NOT_CONNECT(499,"服务器端处理的时间过长"),
        UNEXPECTED_ERROR(-2, "未知错误"),
        JSON_EXCEPTION(-3,"json转换错误");



    }
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {



        if (t.status !=0){
            failure(t.status,t.message)
        }else{
            success(t)
        }

    }

    override fun onError(e: Throwable) {

        if (e is HttpException){


            var htttpErrorType= when(e.code()){

                HtttpErrorType.INTERNAL_SERVER_ERROR.code->HtttpErrorType.INTERNAL_SERVER_ERROR
                HtttpErrorType.BAD_GATEWAY.code->HtttpErrorType.BAD_GATEWAY
                HtttpErrorType.NOT_FOUND.code->HtttpErrorType.BAD_GATEWAY



                else->HtttpErrorType.UNEXPECTED_ERROR


            }
            failure(e.code(),htttpErrorType.msg)
            return

        }

        var htttpErrorType = when (e) {

            is UnknownHostException -> HtttpErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> HtttpErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> HtttpErrorType.CONNECTION_TIMEOUT
            is JSONException->HtttpErrorType.JSON_EXCEPTION

            else -> HtttpErrorType.UNEXPECTED_ERROR
        }
        failure(htttpErrorType.code,htttpErrorType.msg)

    }




}