package com.example.core.data.local

import com.example.core.domain.model.ProductResults

interface LastSeenRepository {
    suspend fun getLastSeen() : List<ProductResults>
    suspend fun addedInLastSeen(productResults: ProductResults)
    suspend fun deleteLastItem()
}