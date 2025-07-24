package com.example.core.data.remote

import com.example.core.domain.model.AccessToken
import com.example.core.domain.usecase.base.ResultStatus

interface AccessTokenRemoteDataSource {

    suspend fun getAccessToken(): ResultStatus<AccessToken>
}