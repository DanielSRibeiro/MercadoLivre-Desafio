package com.example.core.domain.usecase

import com.example.core.data.local.AccessTokenRepository
import com.example.core.domain.model.AccessToken
import com.example.core.domain.usecase.SaveTokenUseCase.Params
import javax.inject.Inject

interface SaveTokenUseCase {
    suspend operator fun invoke(params: Params)
    data class Params(val token: AccessToken)
}

class SaveTokenUseCaseImpl @Inject constructor(
    private val repository: AccessTokenRepository
) : SaveTokenUseCase {
    override suspend fun invoke(params: Params) {
        repository.saveToken(token = params.token)
    }
}