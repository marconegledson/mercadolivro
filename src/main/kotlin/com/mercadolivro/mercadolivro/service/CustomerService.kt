package com.mercadolivro.mercadolivro.service

import com.mercadolivro.mercadolivro.error.Errors
import com.mercadolivro.mercadolivro.exception.NotFoundException
import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.model.Role
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    private val customerRepository: CustomerRepository? = null

    @Autowired
    private val bCrypt: BCryptPasswordEncoder? = null

    fun customers(): List<CustomerModel> {
        return customerRepository!!.findAll().toList()
    }

    fun customers(name: String): List<CustomerModel> {
        return customerRepository!!.findByName(name)
    }

    fun customer(id: Int): CustomerModel {
        return customerRepository!!.findById(id).orElseThrow{ NotFoundException(Errors.ML201.message.format(id), Errors.ML201.name) }
    }

    fun update(id: Int, customer: CustomerModel) {
        var entity : CustomerModel = this.customer(id);
        entity?.let {
            entity.name = customer.email
            entity.email = customer.email
        }
        customerRepository!!.save(customer)
    }

    fun delete(id: Int) {
        customerRepository!!.deleteById(id)
    }

    fun create(customer: CustomerModel): CustomerModel {
        val cc = customer.copy(
            roles = setOf(Role.CUSTOMER),
            password = bCrypt!!.encode(customer.password)

        )
        return customerRepository!!.save(cc)
    }

    fun emailAvaliable(email: String): Boolean {
        return !customerRepository!!.existByEmail(email)
    }


}
