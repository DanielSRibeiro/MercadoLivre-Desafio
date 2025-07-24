package com.example.core.domain.usecase

import com.example.core.data.remote.AccessTokenRemoteRepository
import com.example.core.domain.model.AccessToken
import com.example.core.domain.usecase.base.ResultStatus
import com.example.core.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

fun interface GetAccessTokenUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<AccessToken>>
    data class Params(val params: Unit)
}

class GetAccessTokenUseCaseImpl @Inject constructor(
    private val repository: AccessTokenRemoteRepository
) : UseCase<GetAccessTokenUseCase.Params,AccessToken>(), GetAccessTokenUseCase {

    override suspend fun doWork(params: GetAccessTokenUseCase.Params): ResultStatus<AccessToken> {
        return repository.getAccessToken()
    }
}