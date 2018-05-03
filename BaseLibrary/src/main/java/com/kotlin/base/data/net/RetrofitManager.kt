package com.kotlin.base.data.net

import com.kotlin.base.BaseConstant

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory

import java.util.concurrent.TimeUnit

class RetrofitManager public constructor(){


    private val retrofit: Retrofit;
    //请求拦截器
    private val requestHeadInterceptor:Interceptor


    companion object {
        val mInstance:RetrofitManager by lazy { RetrofitManager() }
    }




    init {
        requestHeadInterceptor= Interceptor {
            chain ->
            var req=chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("charset","utf-8")
                    .build();
            chain.proceed(req)
        }
        retrofit=Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(initClient())
                .build();
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(initLogInterceptor())
                .addInterceptor(requestHeadInterceptor)
                .build();

    }

    private fun initLogInterceptor(): Interceptor {
        var log=HttpLoggingInterceptor()
        log.level=HttpLoggingInterceptor.Level.BODY;

        return log;
    }


    /**
     * 执行http
     */
    fun <T>create(cls:Class<T>): T{

        return retrofit.create(cls);
    }




}