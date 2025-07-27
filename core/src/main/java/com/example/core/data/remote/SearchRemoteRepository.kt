package com.example.core.data.remote

import com.example.core.domain.model.Product
import com.example.core.domain.usecase.base.ResultStatus

interface SearchRemoteRepository {
    suspend fun searchProduct(q: String) : ResultStatus<String>
}