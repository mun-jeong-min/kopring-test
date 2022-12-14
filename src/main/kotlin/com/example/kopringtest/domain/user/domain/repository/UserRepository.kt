package com.example.kopringtest.domain.user.domain.repository

import com.example.kopringtest.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByAccountId(accountId: String): User
}