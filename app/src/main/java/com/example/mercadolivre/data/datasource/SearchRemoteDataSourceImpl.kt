package com.example.mercadolivre.data.datasource

import com.example.core.data.remote.SearchRemoteDataSource
import com.example.core.domain.model.Products
import com.example.mercadolivre.data.remote.response.toProducts
import com.example.mercadolivre.data.remote.service.MLService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val service: MLService
) : SearchRemoteDataSource {

    override suspend fun searchProduct(q: String, offset: Int, limit: Int): Products {
        val response = service.searchProduct(q, offset = offset, limit = limit)
        return response.body()!!.toProducts()

    }
}