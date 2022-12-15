package com.example.kopringtest.domain.user.service

import com.example.kopringtest.domain.user.domain.User
import com.example.kopringtest.domain.user.domain.repository.UserRepository
import com.example.kopringtest.domain.user.present.dto.request.SignUpRequest
import com.example.kopringtest.domain.user.present.dto.response.TokenResponse
import com.example.kopringtest.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun saveUser(request: SignUpRequest): TokenResponse {
        val user = User(
            accountId = request.accountId,
            password = passwordEncoder.encode(request.password)
        )

        userRepository.save(user)
        val accessToken: String = jwtTokenProvider.generateAccessToken(user.accountId)
        val refreshToken: String = jwtTokenProvider.generateRefreshToken(user.accountId)

        return TokenResponse(accessToken, refreshToken)
    }
}