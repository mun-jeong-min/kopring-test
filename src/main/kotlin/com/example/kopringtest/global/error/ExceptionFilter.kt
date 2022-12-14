package com.example.kopringtest.global.error

import com.example.kopringtest.global.error.custom.KopringTestException
import com.example.kopringtest.global.error.exception.InternalServerError
import com.example.kopringtest.global.payload.BaseResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            when (exception) {
                is KopringTestException -> writeError(exception, response);
                else -> writeError(InternalServerError.EXCEPTION, response);
            }
        }
    }

    private fun writeError(e: KopringTestException, response: HttpServletResponse) {
        val errorResponse = BaseResponse.of(e)

        response.status = errorResponse.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}