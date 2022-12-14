package com.example.kopringtest.domain.user.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length

@Entity
@Table(name = "tbl_user")
class User(
    accountId: String,

    password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @NotNull
    var accountId = accountId
        protected set

    @NotNull
    @Length(max = 60)
    var password = password
        protected set
}