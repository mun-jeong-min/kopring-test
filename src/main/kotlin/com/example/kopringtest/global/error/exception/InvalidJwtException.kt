package com.example.kopringtest.global.error.exception

import com.example.kopringtest.global.error.custom.ErrorCode
import com.example.kopringtest.global.error.custom.KopringTestException

object InvalidJwtException : KopringTestException(
    ErrorCode.INVALID_JWT
) {
    val EXCEPTION = InvalidJwtException
}