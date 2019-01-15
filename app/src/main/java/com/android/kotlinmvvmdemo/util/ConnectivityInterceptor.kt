package com.android.kotlinmvvmdemo.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Sachin G. on 6/1/19.
 */
class ConnectivityInterceptor(private val mContext: Context?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Utils.isNetworkAvailable(mContext?.applicationContext)) {
            throw NoConnectivityException("Please check your WIFI or data connection.")
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

class NoConnectivityException(message: String) : Exception(message)