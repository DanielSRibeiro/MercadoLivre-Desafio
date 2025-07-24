package com.example.mercadolivre.data.remote

import com.example.core.data.remote.AccessTokenRemoteDataSource
import com.example.core.domain.model.AccessToken
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.data.remote.AccessTokenRemoteRepository
import javax.inject.Inject

class AccessTokenRemoteRepositoryImpl @Inject constructor(
    private val datasource: AccessTokenRemoteDataSource
) : AccessTokenRemoteRepository {

    override suspend fun getAccessToken(): ResultStatus<AccessToken> {
        return datasource.getAccessToken()
    }
}