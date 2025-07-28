package com.example.core.data.local

import com.example.core.domain.model.ProductResults

interface FavoriteRepository {

    fun getAllFavorites(): List<ProductResults>

    suspend fun isFavorite(id: String): ProductResults?

    suspend fun addedInProduct(productResults: ProductResults)

    suspend fun deleteProduct(productResults: ProductResults)
}