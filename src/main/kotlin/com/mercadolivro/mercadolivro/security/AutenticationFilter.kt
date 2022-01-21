package com.mercadolivro.mercadolivro.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mercadolivro.mercadolivro.exception.AutenticationException
import com.mercadolivro.mercadolivro.model.AutenticationRequest
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    @Autowired
    private val customerRepository: CustomerRepository? = null

    @Autowired
    private val jwtUtil: JWTUtil? = null

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        try {
            val autenticationRequest = jacksonObjectMapper().readValue(request.inputStream, AutenticationRequest::class.java)
            val id = customerRepository!!.findByEmail(autenticationRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, autenticationRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            throw AutenticationException("Falha ao autenticar", "999")
        }

    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val id = (authResult.principal as UserCustomDetails).customerModel.id
        val generateToken = jwtUtil!!.generateToken(id)
        response.addHeader("Authorization", "Bearer: $generateToken")
    }


}
