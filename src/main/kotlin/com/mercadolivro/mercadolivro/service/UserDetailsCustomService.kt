package com.mercadolivro.mercadolivro.service

import com.mercadolivro.mercadolivro.exception.AutenticationException
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import com.mercadolivro.mercadolivro.security.UserCustomDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService() : UserDetailsService {

    @Autowired
    private val customerRepository: CustomerRepository? = null

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository!!.findById(id.toInt()).orElseThrow { AutenticationException("usuario nao encontratdo", "999") }
        return UserCustomDetails(customer)
    }
}
