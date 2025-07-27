package com.example.core.domain.usecase

import com.example.core.data.local.AccessTokenRepository
import com.example.core.domain.model.AccessToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetTokenUseCase {
    operator fun invoke(): AccessToken?
}

class GetTokenUseCaseImpl @Inject constructor(
    private val repository: AccessTokenRepository
) : GetTokenUseCase {

    override fun invoke(): AccessToken? {
        return repository.getToken()
    }
}