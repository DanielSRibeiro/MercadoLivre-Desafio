package com.example.mercadolivre.data.repository

import com.example.core.data.local.FavoriteDataSource
import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {
    override fun getAllFavorites(): List<Product> {
        return favoriteDataSource.getAllFavorites()
    }

    override suspend fun isFavorite(id: String): Product? {
        return favoriteDataSource.isFavorite(id)
    }

    override suspend fun addedInProduct(product: Product) {
        return favoriteDataSource.addedInProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return favoriteDataSource.deleteProduct(product)
    }

}