package com.mercadolivro.mercadolivro.controller

import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.security.OnlyAccessPermission
import com.mercadolivro.mercadolivro.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customer")
class CustomerController {

    @Autowired
    val customerService: CustomerService? = null

    @GetMapping
    fun customers(): List<CustomerModel> {
        return customerService!!.customers()
    }

    @GetMapping("/filter")
    fun customers(@RequestParam name: String?): List<CustomerModel> {
        return customerService!!.customers(name = "")  ?: listOf()
    }

    @GetMapping("/{id}")
    @OnlyAccessPermission
    fun customer(@PathVariable id: Int): CustomerModel? {
        return customerService!!.customer(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @Valid @RequestBody customer: CustomerModel) {
        customerService!!.update(id, customer)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService!!.delete(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody customer: CustomerModel): CustomerModel? {
        return customerService!!.create(customer)
    }

}
