package com.example.mercadolivre.framework.data.datasource

import com.example.core.data.remote.AccessTokenRemoteDataSource
import com.example.core.domain.ErrorStates
import com.example.core.domain.model.AccessToken
import com.example.core.domain.usecase.base.ResultStatus
import com.example.mercadolivre.framework.data.remote.service.AccessTokenService
import com.example.mercadolivre.framework.data.remote.request.AccessTokenRequest
import com.example.mercadolivre.framework.data.remote.response.toRefreshToken
import com.example.mercadolivre.util.Constants
import javax.inject.Inject

class AccessTokenRemoteDataSourceImpl @Inject constructor(
    private val accessTokenService: AccessTokenService
) : AccessTokenRemoteDataSource {

    override suspend fun getAccessToken(): ResultStatus<AccessToken> {
        val response = accessTokenService.getAccessToken(
            request = AccessTokenRequest(
                grantType = Constants.REFRESH_TOKEN,
                clientId = Constants.CLIENT_ID,
                clientSecret = Constants.CLIENT_SECRET,
                refreshToken = Constants.TG_TOKEN
            )
        )
        if (response.isSuccessful && response.body() != null) {
            return ResultStatus.Success(response.body()!!.toRefreshToken())
        } else {
            var message = response.code().toString()
            if (response.body() != null) {
                message += "- ${response.body()}"
            }
            if (response.message().isNotBlank()) {
                message += "- ${response.message()}"
            }

            return when (response.code()) {
                401 -> ResultStatus.Failure(ErrorStates.Unauthorized, message)
                else -> ResultStatus.Failure(ErrorStates.Exception, message)
            }
        }
    }

}