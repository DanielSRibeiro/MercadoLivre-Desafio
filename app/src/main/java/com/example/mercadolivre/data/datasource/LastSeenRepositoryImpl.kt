package com.example.mercadolivre.data.datasource

import com.example.core.data.local.LastSeenDataSource
import com.example.core.data.local.LastSeenRepository
import com.example.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LastSeenRepositoryImpl @Inject constructor(
    private val lastSeenDataSource: LastSeenDataSource
) : LastSeenRepository {
    override suspend fun getLastSeen(): List<Product> {
        return lastSeenDataSource.getLastSeen()
    }

    override suspend fun addedInLastSeen(product: Product) {
        lastSeenDataSource.addedInLastSeen(product = product)
    }
}