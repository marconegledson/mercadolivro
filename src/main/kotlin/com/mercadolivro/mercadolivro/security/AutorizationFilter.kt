package com.mercadolivro.mercadolivro.security

import com.mercadolivro.mercadolivro.exception.AutenticationException
import com.mercadolivro.mercadolivro.service.UserDetailsCustomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutorizationFilter(authenticationManager: AuthenticationManager)  : BasicAuthenticationFilter(authenticationManager) {

    @Autowired
    private val jwtUtil: JWTUtil? = null

    @Autowired
    private val userDetailsCustomService: UserDetailsCustomService? = null

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader("Authorization")
        if(authorization != null && authorization.startsWith(" Bearer")) {
            val token = authorization.split(" ")[1]
            val auth = getAutentication(token)
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    private fun getAutentication(token: String): UsernamePasswordAuthenticationToken {
        if(!jwtUtil!!.isValidToken(token)) {
           throw AutenticationException("Token invalido", "999")
        }
        val subject = jwtUtil.getSubject(token)
        val customer = userDetailsCustomService!!.loadUserByUsername(subject)
        return UsernamePasswordAuthenticationToken(customer, null, customer.authorities)

    }
}
