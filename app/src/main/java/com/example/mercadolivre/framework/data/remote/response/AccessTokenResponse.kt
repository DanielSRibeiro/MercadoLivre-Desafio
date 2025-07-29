package com.example.mercadolivre.framework.data.remote.response

import com.example.core.domain.model.AccessToken
import com.google.gson.annotations.SerializedName
import java.time.Instant

data class AccessTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("scope") val scope: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("refresh_token") val refreshToken: String
)

fun AccessTokenResponse.toRefreshToken() : AccessToken {
    val timeExpiresAt = Instant.now().plusSeconds(this.expiresIn.toLong())

    return AccessToken(
        accessToken = this.accessToken,
        expiresAt = timeExpiresAt.epochSecond,
    )
}
