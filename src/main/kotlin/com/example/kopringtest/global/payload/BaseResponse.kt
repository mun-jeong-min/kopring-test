package com.example.kopringtest.global.payload

import com.example.kopringtest.global.error.custom.KopringTestException
import com.fasterxml.jackson.annotation.JsonInclude

class BaseResponse<T>(
    val status: Int,
    val message: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val content: T?
) {
    companion object {
        fun of(exception: KopringTestException): BaseResponse<Unit> {
            return BaseResponse(
                status = exception.status,
                message = exception.errorMessage,
                content = null
            )
        }
    }
}