package com.example.kopringtest.global.error.custom

import com.example.kopringtest.global.error.property.ErrorProperty

enum class ErrorCode(
    override val status: Int,
    override val errorMessage: String
) : ErrorProperty {
    BAD_REQUEST(400, "요청이 잘못되었습니다."),

    INVALID_JWT(401, "jwt 인증 실패"),
    EXPIRED_JWT(401, "jwt 기간 만료"),

    USER_NOT_FOUND(404, "유저를 찾지 못함"),

    SERVER_INTERNAL_ERROR(500, "서버 에러입니다.")
}