package com.example.core.data.local

import com.example.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteDataSource {

    fun getAllFavorites(): List<Product>

    suspend fun isFavorite(id: String): Product?

    suspend fun addedInProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}