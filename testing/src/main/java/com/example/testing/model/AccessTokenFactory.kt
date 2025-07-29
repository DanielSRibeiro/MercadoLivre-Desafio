package com.example.testing.model

import com.example.core.domain.model.AccessToken
import java.time.Instant

class AccessTokenFactory {

    fun create(token: TokenIsExpired) = when (token) {
        is TokenIsExpired.Yes -> {
            AccessToken(
                accessToken = "123",
                expiresAt = 1000
            )
        }

        is TokenIsExpired.No -> AccessToken(
            accessToken = "321",
            expiresAt = (Instant.now().epochSecond * 2)
        )

    }

    sealed class TokenIsExpired {
        data object Yes : TokenIsExpired()
        data object No : TokenIsExpired()
    }
}