package com.example.mercadolivre.data.remote.service

import com.example.mercadolivre.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MLService {

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") q: String
    ) : Response<SearchResponse>
}