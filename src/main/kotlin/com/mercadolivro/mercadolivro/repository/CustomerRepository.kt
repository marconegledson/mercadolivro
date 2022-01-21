package com.mercadolivro.mercadolivro.repository

import com.mercadolivro.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<CustomerModel, Int> {

    fun findByName(name : String) : List<CustomerModel>
    fun existByEmail(email: String): Boolean
    fun findByEmail(email: String): CustomerModel?

}
