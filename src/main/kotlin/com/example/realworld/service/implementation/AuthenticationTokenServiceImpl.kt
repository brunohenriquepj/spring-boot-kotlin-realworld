package com.example.realworld.service.implementation

import com.example.realworld.entity.User
import com.example.realworld.service.AuthenticationTokenService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationTokenServiceImpl : AuthenticationTokenService {
    @Value("\${jwt.secret}")
    val secret: String? = null

    @Value("\${jwt.expiration-milliseconds}")
    val expiration: Long? = null

    override fun generateToken(user: User): String {
        val currentDate = Date()
        val expirationDate = Date(currentDate.time + expiration!!)

        return Jwts.builder()
            .setIssuer("RealWorld Api")
            .setSubject(user.email)
            .setIssuedAt(currentDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }
}