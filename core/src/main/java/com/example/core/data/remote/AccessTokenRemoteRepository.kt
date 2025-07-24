package com.example.core.data.remote

import com.example.core.domain.model.AccessToken
import com.example.core.domain.usecase.base.ResultStatus

interface AccessTokenRemoteRepository {

    suspend fun getAccessToken() : ResultStatus<AccessToken>
}