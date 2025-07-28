package com.example.core.data.remote

import com.example.core.domain.model.Products
import com.example.core.domain.usecase.base.ResultStatus

interface SearchRemoteDataSource {
    suspend fun searchProduct(q: String, offset: Int, limit: Int) : Products
}