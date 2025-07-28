package com.example.core.data.remote

import androidx.paging.PagingSource
import com.example.core.domain.model.ProductResults

interface SearchRemoteRepository {
    fun searchProduct(q: String) : PagingSource<Int, ProductResults>
}