package com.example.mercadolivre.data.repository

import com.example.core.data.remote.SearchRemoteDataSource
import com.example.core.data.remote.SearchRemoteRepository
import com.example.core.domain.usecase.base.ResultStatus
import javax.inject.Inject

class SearchRemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource
) : SearchRemoteRepository {

    override suspend fun searchProduct(q: String): ResultStatus<String> {
        return remoteDataSource.searchProduct(q)
    }

}