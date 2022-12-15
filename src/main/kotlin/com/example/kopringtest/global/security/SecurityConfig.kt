package com.example.kopringtest.global.security

import com.example.kopringtest.global.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) {
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin().disable()
            .csrf().disable()
            .cors()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST,"/user/signup").permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(FilterConfig(jwtTokenProvider, objectMapper))

        return http.build()
    }

    @Bean
    protected fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}