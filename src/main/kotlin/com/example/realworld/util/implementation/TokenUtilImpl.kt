package com.example.realworld.util.implementation

import com.example.realworld.util.TokenUtil
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class TokenUtilImpl(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration-milliseconds}") private val expiration: Long
) : TokenUtil {
    override fun generateToken(userEmail: String): String {
        val currentDate = Date()
        val expirationDate = Date(currentDate.time + expiration)

        return Jwts.builder()
            .setIssuer("RealWorld Api")
            .setSubject(userEmail)
            .setIssuedAt(currentDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    override fun getTokenSubject(token: String?): String? {
        if (token.isNullOrBlank()) return null

        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body.subject
        } catch (exception: JwtException) {
            null
        }
    }
}
