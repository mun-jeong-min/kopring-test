package com.example.kopringtest.global.error

import com.example.kopringtest.global.error.custom.ErrorCode
import com.example.kopringtest.global.error.custom.KopringTestException
import com.example.kopringtest.global.error.exception.BadRequestException
import com.example.kopringtest.global.payload.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Unit>> {
        return exceptionHandler(BadRequestException.EXCEPTION)
    }

    @ExceptionHandler(KopringTestException::class)
    fun customException(e: KopringTestException): ResponseEntity<BaseResponse<Unit>> {
        return exceptionHandler(e)
    }

    private fun exceptionHandler(e: KopringTestException): ResponseEntity<BaseResponse<Unit>> {
        val status = HttpStatus.valueOf(e.status)
        val body = BaseResponse.of(e)
        return ResponseEntity(body, status)
    }

}