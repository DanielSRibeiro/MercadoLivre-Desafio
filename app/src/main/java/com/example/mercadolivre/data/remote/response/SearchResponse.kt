package com.example.mercadolivre.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("paging")
    val pagingResponse: PagingResponse,
    @SerializedName("query_type")
    val queryType: String,
    @SerializedName("results")
    val results: List<SearchDataResponse>,
    @SerializedName("used_attributes")
    val usedAttributes: List<Any>
)