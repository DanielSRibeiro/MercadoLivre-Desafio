package com.example.mercadolivre.framework.data.remote.service

import com.example.mercadolivre.framework.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MLService {

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ) : Response<SearchResponse>
}