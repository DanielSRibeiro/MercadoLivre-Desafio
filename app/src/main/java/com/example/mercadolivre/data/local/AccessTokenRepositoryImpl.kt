package com.example.mercadolivre.data.local

import com.example.core.data.local.AccessTokenDataSource
import com.example.core.data.local.AccessTokenRepository
import com.example.core.domain.model.AccessToken
import javax.inject.Inject

class AccessTokenRepositoryImpl @Inject constructor(
    private val dataSource: AccessTokenDataSource
) : AccessTokenRepository {

    override suspend fun saveToken(token: AccessToken) {
        dataSource.saveToken(token)
    }

    override suspend fun getToken(): AccessToken? {
        return dataSource.getToken()
    }
}