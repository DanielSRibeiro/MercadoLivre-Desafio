package com.example.mercadolivre.framework.data.repository

import androidx.paging.PagingSource
import com.example.core.data.remote.SearchRemoteDataSource
import com.example.core.data.remote.SearchRemoteRepository
import com.example.core.domain.model.ProductResults
import com.example.mercadolivre.framework.paging.SearchPagingSource
import javax.inject.Inject

class SearchRemoteRepositoryImpl @Inject constructor(
    private val dataSource: SearchRemoteDataSource
) : SearchRemoteRepository {

    override fun searchProduct(q: String): PagingSource<Int, ProductResults> {
        return SearchPagingSource(
            query = q,
            dataSource = dataSource
        )
    }

}