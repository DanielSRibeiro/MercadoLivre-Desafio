package com.example.mercadolivre.data.datasource

import com.example.core.data.local.LastSeenDataSource
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.data.local.dao.MlDao
import com.example.mercadolivre.data.local.entity.mapper.toLastSeenEntity
import com.example.mercadolivre.data.local.entity.mapper.toProductResult
import javax.inject.Inject

class LastSeenDataSourceImpl @Inject constructor(
    private val mlDao: MlDao
) : LastSeenDataSource {

    override suspend fun getLastSeen(): List<ProductResults> {
        return mlDao.getLastSeen().map { it.toProductResult() }
    }

    override suspend fun addedInLastSeen(productResults: ProductResults) {
        mlDao.addedInLastSeen(productResults.toLastSeenEntity())
    }
}