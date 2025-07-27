package com.example.core.data.local

import com.example.core.domain.model.AccessToken

interface AccessTokenRepository {
    fun saveToken(token: AccessToken)
    fun getToken(): AccessToken?
}