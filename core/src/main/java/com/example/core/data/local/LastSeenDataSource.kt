package com.example.core.data.local

import com.example.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface LastSeenDataSource {
    suspend fun getLastSeen() : List<Product>
    suspend fun addedInLastSeen(product: Product)
}