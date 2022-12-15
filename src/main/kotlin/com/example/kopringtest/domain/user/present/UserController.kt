package com.example.kopringtest.domain.user.present

import com.example.kopringtest.domain.user.present.dto.request.SignUpRequest
import com.example.kopringtest.domain.user.present.dto.response.TokenResponse
import com.example.kopringtest.domain.user.service.SignUpService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val signUpService: SignUpService
) {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(@Valid @RequestBody signUpRequest: SignUpRequest): TokenResponse {
        return signUpService.saveUser(signUpRequest)
    }
}