package com.example.kopringtest.domain.auth.domain.repository

import com.example.kopringtest.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
}