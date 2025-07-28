package com.example.mercadolivre.data.remote.response

import com.example.core.domain.model.Attribute
import com.example.core.domain.model.Paging
import com.example.core.domain.model.Picture
import com.example.core.domain.model.ProductResults
import com.example.core.domain.model.Products
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


fun SearchResponse.toProducts() = Products(
    keywords = this.keywords,
    paging = Paging(
        total = this.pagingResponse.total,
        limit = this.pagingResponse.limit,
        offset = this.pagingResponse.offset
    ),
    results = this.results.map {
        ProductResults(
            id = it.id,
            name = it.name,
            total = this.pagingResponse.total,
            keywords = this.keywords,
            pictures = it.pictureResponses.map { picture ->
                Picture(
                    id = picture.id,
                    url = picture.url
                )
            },
            attribute = it.attributeResponses.map { attribute ->
                Attribute(
                    name = attribute.name,
                    valueName = attribute.valueName
                )
            },
        )
    }
)