package com.example.kopringtest.global.error.exception

import com.example.kopringtest.global.error.custom.ErrorCode
import com.example.kopringtest.global.error.custom.KopringTestException

object BadRequestException: KopringTestException(
    ErrorCode.BAD_REQUEST
) {
    val EXCEPTION = BadRequestException
}