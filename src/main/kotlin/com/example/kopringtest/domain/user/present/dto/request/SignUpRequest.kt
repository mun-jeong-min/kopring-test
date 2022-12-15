package com.example.kopringtest.domain.user.present.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    val accountId: String,

    val password: String
)