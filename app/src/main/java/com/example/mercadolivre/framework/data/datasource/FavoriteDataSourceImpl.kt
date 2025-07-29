package com.example.mercadolivre.framework.data.datasource

import com.example.core.data.local.FavoriteDataSource
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.framework.data.local.dao.MlDao
import com.example.mercadolivre.framework.data.local.entity.mapper.toProductEntity
import javax.inject.Inject

class FavoriteDataSourceImpl @Inject constructor(
    private val mlDao: MlDao
) : FavoriteDataSource {

    override fun getAllFavorites(): List<ProductResults> {
        return mlDao.getAllFavorites().map { it.toProductEntity() }
    }

    override suspend fun isFavorite(id: String): ProductResults? {
        return mlDao.isFavorite(id)?.toProductEntity()
    }

    override suspend fun addedInProduct(productResults: ProductResults) {
        mlDao.addedInProduct(productResults.toProductEntity())
    }

    override suspend fun deleteProduct(productResults: ProductResults) {
        mlDao.deleteProduct(productResults.toProductEntity())
    }
}