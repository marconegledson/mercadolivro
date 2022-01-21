package com.mercadolivro.mercadolivro.service

import com.mercadolivro.mercadolivro.error.Errors
import com.mercadolivro.mercadolivro.exception.NotFoundException
import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.model.Status
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var  bCrypt: BCryptPasswordEncoder

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {
        //given
        every { customerRepository.findAll() } returns listOf()

        //when
        val customers = customerService.customers()

        //then
        assertNotNull(customers)
        assertTrue(customers.isEmpty())
        verify(exactly = 1) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByName(any()) }
    }

    @Test
    fun `should return customer by id`() {
        val fake = CustomerModel(name = "marcone", email = "aa@aia", password = "baba", status = Status.ATIVO)

        every { customerRepository.findById(any()) } returns Optional.of(fake)

        val customer = customerRepository.findById(1)
        assertEquals(fake, customer)
        verify(exactly = 1) { customerRepository.findAllById(any()) }
    }

    @Test
    fun `should thrown exception when customer not found`() {
        every { customerRepository.findById(any()) } returns Optional.empty()

        val error = assertThrows<NotFoundException> { customerRepository.findById(1) }

        assertEquals(Errors.ML201.name,  error.errorCode)
        verify(exactly = 1) { customerRepository.findAllById(any()) }
    }


    @Test
    fun `should delete a customer`() {
        every { customerRepository.deleteById(any()) } just runs

        verify(exactly = 1) { customerRepository.deleteById(any()) }
    }



}
