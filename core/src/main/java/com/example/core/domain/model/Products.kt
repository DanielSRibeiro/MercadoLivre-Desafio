package com.example.core.domain.model

data class Products(
    val keywords: String,
    val paging: Paging,
    val results: List<ProductResults>
)