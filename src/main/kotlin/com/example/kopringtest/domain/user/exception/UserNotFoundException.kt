package com.example.kopringtest.domain.user.exception

import com.example.kopringtest.global.error.custom.ErrorCode
import com.example.kopringtest.global.error.custom.KopringTestException

object UserNotFoundException : KopringTestException(
    ErrorCode.USER_NOT_FOUND
) {
    val EXCEPTION = UserNotFoundException
}