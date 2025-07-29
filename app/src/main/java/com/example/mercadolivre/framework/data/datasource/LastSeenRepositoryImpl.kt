package com.example.mercadolivre.framework.data.datasource

import com.example.core.data.local.LastSeenDataSource
import com.example.core.data.local.LastSeenRepository
import com.example.core.domain.model.ProductResults
import javax.inject.Inject

class LastSeenRepositoryImpl @Inject constructor(
    private val lastSeenDataSource: LastSeenDataSource
) : LastSeenRepository {
    override suspend fun getLastSeen(): List<ProductResults> {
        return lastSeenDataSource.getLastSeen()
    }

    override suspend fun addedInLastSeen(productResults: ProductResults) {
        lastSeenDataSource.addedInLastSeen(productResults = productResults)
    }

    override suspend fun deleteLastItem() {
        lastSeenDataSource.deleteLastItem()
    }
}