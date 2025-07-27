package com.example.mercadolivre.data.remote.response


import com.google.gson.annotations.SerializedName

data class AttributeResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("value_id")
    val valueId: String,
    @SerializedName("value_name")
    val valueName: String
)