package com.example.core.data.local

import com.example.core.domain.model.AccessToken

interface AccessTokenRepository {
    suspend fun saveToken(token: AccessToken)
    suspend fun getToken(): AccessToken?
}