package com.example.kopringtest.global.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseTimeEntity {
    @Column(nullable = false, updatable = false)
    val createTime: LocalDateTime = LocalDateTime.now()
}