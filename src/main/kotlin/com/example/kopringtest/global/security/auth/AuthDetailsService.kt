package com.example.kopringtest.global.security.auth

import com.example.kopringtest.domain.user.domain.repository.UserRepository
import com.example.kopringtest.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(accountId: String): UserDetails {
        return AuthDetails(
            userRepository.findByAccountId(accountId) ?: throw UserNotFoundException.EXCEPTION
        )
    }
}