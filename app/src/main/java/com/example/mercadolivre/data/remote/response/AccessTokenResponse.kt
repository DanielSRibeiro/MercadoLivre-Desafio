package com.example.mercadolivre.data.remote.response

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
fun AccessTokenResponse.toRefreshToken() : AccessToken {
    val timeExpiresAt = Instant.now().plusSeconds(this.expiresIn.toLong())

    return AccessToken(
        accessToken = this.accessToken,
        tokenType = this.tokenType,
        expiresIn = this.expiresIn,
        expiresAt = timeExpiresAt.epochSecond,
        scope = this.scope,
        userId = this.userId,
        refreshToken = this.refreshToken,
    )
}
