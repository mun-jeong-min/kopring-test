package com.example.kopringtest.domain.user.present.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)