package com.example.core.domain.model

data class ProductResults(
    val id: String,
    val name: String,
    val pictures: List<Picture>,
    val attribute: List<Attribute>,
    val keywords: String,
    val total: Int,
)
