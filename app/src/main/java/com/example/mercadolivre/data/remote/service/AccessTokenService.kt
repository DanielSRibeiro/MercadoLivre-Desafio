package com.example.mercadolivre.data.remote.service

import com.example.mercadolivre.data.remote.request.AccessTokenRequest
import com.example.mercadolivre.data.remote.response.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessTokenService {

    @POST("oauth/token")
    suspend fun getAccessToken(
        @Body request: AccessTokenRequest
    ) : Response<AccessTokenResponse>
}