package com.example.kopringtest.global.error.exception

import com.example.kopringtest.global.error.custom.ErrorCode
import com.example.kopringtest.global.error.custom.KopringTestException

object ExpiredJwtException : KopringTestException(
    ErrorCode.EXPIRED_JWT
) {
    val EXCEPTION = ExpiredJwtException
}