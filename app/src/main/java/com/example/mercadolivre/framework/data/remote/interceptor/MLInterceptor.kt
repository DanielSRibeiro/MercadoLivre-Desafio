package com.example.mercadolivre.framework.data.remote.interceptor

import com.example.core.domain.usecase.GetTokenUseCase
import com.example.mercadolivre.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MLInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val accessToken = getTokenUseCase.invoke()?.accessToken.orEmpty()

        val originalUrl = request.url
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("site_id", Constants.SITE_ID_VALUE)
            .addQueryParameter("status", "active")
            .build()

        val newRequest = request.newBuilder()
            .addHeader(Constants.AUTHORIZATION_PARAM, "Bearer $accessToken")
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}