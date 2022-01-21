package com.mercadolivro.mercadolivro.repository

import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.model.Status
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun init(){
        customerRepository.deleteAll()
    }

    @Test
    fun `should name contains`() {
        val cus = CustomerModel(name = "marcone", email = "aa@aia", password = "baba", status = Status.ATIVO)
        customerRepository.save(cus)

        val customers = customerRepository.findByName("marcone")

        Assertions.assertEquals(listOf(cus), customers)
    }

    @Nested
    inner class `exist by email` {
        @Test
        fun `should return when email passed`(){

            val cus = CustomerModel(name = "marcone", email = "aa@aia", password = "baba", status = Status.ATIVO)
            customerRepository.save(cus)

            val exists = customerRepository.existByEmail("aa@aia")

            Assertions.assertTrue(exists)

        }

        @Test
        fun `should not return when email passed`(){
            val cus = CustomerModel(name = "marcone", email = "aa@aia", password = "baba", status = Status.ATIVO)
            customerRepository.save(cus)

            val exists = customerRepository.existByEmail("esa")

            Assertions.assertTrue(exists)

        }
    }

}
