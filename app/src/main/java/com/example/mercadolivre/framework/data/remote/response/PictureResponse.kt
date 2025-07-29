package com.example.mercadolivre.framework.data.remote.response


import com.google.gson.annotations.SerializedName

data class PictureResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)