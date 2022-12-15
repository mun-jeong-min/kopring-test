package com.example.kopringtest.global.security.jwt

import com.example.kopringtest.domain.auth.domain.RefreshToken
import com.example.kopringtest.domain.auth.domain.repository.RefreshTokenRepository
import com.example.kopringtest.global.error.exception.InvalidJwtException
import com.example.kopringtest.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun generateRefreshToken(id: String): String {
        val token: String = generateToken(id, "refresh", jwtProperties.refreshExp)

        val refreshToken = RefreshToken(
            accountId = id,
            token = token,
            ttl = jwtProperties.refreshExp
        )

        refreshTokenRepository.save(refreshToken)

        return token
    }

    fun generateAccessToken(id: String): String {
        return generateToken(id, "access", jwtProperties.accessExp)
    }

    private fun generateToken(id: String, type: String, exp: Long): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setSubject(id)
            .claim("typ", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
    }

    fun parseToken(bearerToken: String?): String? {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.jwtPrefix)) {
            return bearerToken.replace(jwtProperties.jwtPrefix, "")
        }
        throw InvalidJwtException.EXCEPTION
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer: String = request.getHeader(jwtProperties.jwtHeader)
        return parseToken(bearer)
    }

    fun getTokenBody(token: String): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token).getBody()
        } catch (e: ExpiredJwtException) {
            throw com.example.kopringtest.global.error.exception.ExpiredJwtException.EXCEPTION
        } catch (e: Exception) {
            throw InvalidJwtException.EXCEPTION
        }
    }

    fun getTokenSubject(token: String): String {
        return getTokenBody(token).subject
    }

    fun authentication(token: String): Authentication {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}