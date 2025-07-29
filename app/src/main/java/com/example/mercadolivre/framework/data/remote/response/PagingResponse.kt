package com.example.mercadolivre.framework.data.remote.response


import com.google.gson.annotations.SerializedName

data class PagingResponse(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)