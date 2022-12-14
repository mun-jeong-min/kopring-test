package com.example.kopringtest.global.error.exception

import com.example.kopringtest.global.error.custom.ErrorCode
import com.example.kopringtest.global.error.custom.KopringTestException

object InternalServerError : KopringTestException(
    ErrorCode.SERVER_INTERNAL_ERROR
) {
    val EXCEPTION = InternalServerError
}