package com.example.kopringtest.global.error.custom

import com.example.kopringtest.global.error.property.ErrorProperty

enum class ErrorCode(
    override val status: Int,
    override val errorMessage: String
) : ErrorProperty {
    BAD_REQUEST(400, "요청이 잘못되었습니다."),

    SERVER_INTERNAL_ERROR(500, "서버 에러입니다.")
}