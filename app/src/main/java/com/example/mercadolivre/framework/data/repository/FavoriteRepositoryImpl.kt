package com.example.mercadolivre.framework.data.repository

import com.example.core.data.local.FavoriteDataSource
import com.example.core.data.local.FavoriteRepository
import com.example.core.domain.model.ProductResults
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {
    override fun getAllFavorites(): List<ProductResults> {
        return favoriteDataSource.getAllFavorites()
    }

    override suspend fun isFavorite(id: String): ProductResults? {
        return favoriteDataSource.isFavorite(id)
    }

    override suspend fun addedInProduct(productResults: ProductResults) {
        return favoriteDataSource.addedInProduct(productResults)
    }

    override suspend fun deleteProduct(productResults: ProductResults) {
        return favoriteDataSource.deleteProduct(productResults)
    }

}