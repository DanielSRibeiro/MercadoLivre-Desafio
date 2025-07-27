package com.example.mercadolivre.data.remote.response


import com.google.gson.annotations.SerializedName

data class SearchDataResponse(
    @SerializedName("attributes")
    val attributeResponses: List<AttributeResponse>,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("catalog_product_id")
    val catalogProductId: String,
    @SerializedName("channels")
    val channels: List<Any>,
    @SerializedName("children_ids")
    val childrenIds: List<Any>,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("main_features")
    val mainFeatures: List<Any>,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: String,
    @SerializedName("pdp_types")
    val pdpTypes: List<Any>,
    @SerializedName("pictures")
    val pictureResponses: List<PictureResponse>,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("quality_type")
    val qualityType: String,
    @SerializedName("settings")
    val settingsResponse: SettingsResponse,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("variations")
    val variations: List<Any>
)