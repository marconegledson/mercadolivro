package com.mercadolivro.mercadolivro.security

import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.model.Status
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(var customerModel: CustomerModel) : UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return customerModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()
    }

    override fun getPassword(): String = customerModel.password
    override fun getUsername(): String = customerModel.id.toString()
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean  = true
    override fun isEnabled(): Boolean  = customerModel.status == Status.ATIVO
}
