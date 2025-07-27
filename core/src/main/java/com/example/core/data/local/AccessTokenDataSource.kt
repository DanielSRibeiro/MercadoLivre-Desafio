package com.example.core.data.local

import com.example.core.domain.model.AccessToken

interface AccessTokenDataSource {
    fun saveToken(token: AccessToken)
    fun getToken(): AccessToken?
}