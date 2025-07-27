package com.example.core.domain.model

data class Product(
    val id: String,
    val data: ProductResults
)

data class ProductResults(
    val title: String,
    val description: String,
    val price: Double,
    val basePrice: Double = 0.0,
    val thumbnail: String,
    val pictures: List<PicturesResponse>
)

data class PicturesResponse(
    val id: String,
    val url: String,
)