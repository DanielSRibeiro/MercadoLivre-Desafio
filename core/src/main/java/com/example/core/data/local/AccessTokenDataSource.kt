package com.example.core.data.local

import com.example.core.domain.model.AccessToken

interface AccessTokenDataSource {
    suspend fun saveToken(token: AccessToken)
    suspend fun getToken(): AccessToken?
}