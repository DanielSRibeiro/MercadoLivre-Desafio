package com.example.mercadolivre.framework.data.remote.interceptor

import com.example.mercadolivre.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(Constants.ACCEPT_PARAM,Constants.ACCEPT_VALUE)
            .addHeader(Constants.CONTENT_TYPE_PARAM,Constants.CONTENT_TYPE_VALUE)
            .build()

        return chain.proceed(newRequest)
    }
}