package com.example.mercadolivre.framework.data.remote.response


import com.google.gson.annotations.SerializedName

data class SettingsResponse(
    @SerializedName("exclusive")
    val exclusive: Boolean,
    @SerializedName("listing_strategy")
    val listingStrategy: String
)