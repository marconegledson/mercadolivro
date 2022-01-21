package com.mercadolivro.mercadolivro.security

import com.mercadolivro.mercadolivro.exception.AutenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {

    @Value("\${jwt.secret.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int?): String? {
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        return claims.subject == null || claims.expiration == null || Date().after(claims.expiration)
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        } catch(e : Exception) {
            throw AutenticationException("Token invalido", "999")
        }
    }

    fun getSubject(token: String) : String = getClaims(token).subject
}
