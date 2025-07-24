package com.example.core.domain.model

class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val expiresAt: Long?,
    val scope: String,
    val userId: Int,
    val refreshToken: String
)