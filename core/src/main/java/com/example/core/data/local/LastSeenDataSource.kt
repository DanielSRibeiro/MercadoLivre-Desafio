package com.example.core.data.local

import com.example.core.domain.model.ProductResults

interface LastSeenDataSource {
    suspend fun getLastSeen() : List<ProductResults>
    suspend fun addedInLastSeen(productResults: ProductResults)
}