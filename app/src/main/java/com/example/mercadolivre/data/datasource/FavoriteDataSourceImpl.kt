package com.example.mercadolivre.data.datasource

import com.example.core.data.local.FavoriteDataSource
import com.example.core.domain.model.PicturesResponse
import com.example.core.domain.model.Product
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.data.local.dao.MlDao
import com.example.mercadolivre.data.local.entity.mapper.toLastSeenEntity
import com.example.mercadolivre.data.local.entity.mapper.toProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteDataSourceImpl @Inject constructor(
    private val mlDao: MlDao
) : FavoriteDataSource {

    override fun getAllFavorites(): List<Product> {
        return mlDao.getAllFavorites().map { it.toProductEntity() }
    }

    override suspend fun isFavorite(id: String): Product? {
        return mlDao.isFavorite(id)?.toProductEntity()
    }

    override suspend fun addedInProduct(product: Product) {
        mlDao.addedInProduct(product.toProductEntity())
    }

    override suspend fun deleteProduct(product: Product) {
        mlDao.deleteProduct(product.toProductEntity())
    }
}