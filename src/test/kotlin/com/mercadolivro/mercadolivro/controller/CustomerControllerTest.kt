package com.mercadolivro.mercadolivro.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mercadolivro.mercadolivro.model.CustomerModel
import com.mercadolivro.mercadolivro.repository.CustomerRepository
import com.mercadolivro.mercadolivro.security.UserCustomDetails
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
@WithMockUser
class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun init() {
        customerRepository.deleteAll()
    }

    @AfterEach
    fun tearDown() {
        customerRepository.deleteAll()
    }

    @Test
    fun `should returns all customers`() {
        val customer = CustomerModel(name = " Marcone", email = "aa@aa.com", password = "12e1aca")
        val customer1 = customerRepository.save(customer)

        mockMvc
            .perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(customer1.id))

    }

    @Test
    fun `should returns all customers by name`() {
        val customer = CustomerModel(name = " Marcone", email = "aa@aa.com", password = "12e1aca")
        val customer1 = customerRepository.save(customer)

        mockMvc
            .perform(get("/customers?name=Mar"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].id").value(customer1.id))

    }

    @Test
    fun `should create a customer`() {
        val customer = CustomerModel(name = " Marcone", email = "aa@aa.com", password = "12e1aca")

        mockMvc
            .perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isCreated)

        val customers = customerRepository.findAll().toList()

        assertEquals(1, customers.size)
    }

    @Test
    fun `should get user`() {
        val customer = CustomerModel(name = " Marcone", email = "aa@aa.com", password = "12e1aca")
        val customer1 = customerRepository.save(customer)

        mockMvc
            .perform(get("/${customer1.id}").with(user(UserCustomDetails(customer))))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(customer1.id))

    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun `should get user is admin`() {
        val customer = CustomerModel(name = " Marcone", email = "aa@aa.com", password = "12e1aca")
        val customer1 = customerRepository.save(customer)

        mockMvc
            .perform(get("/${customer1.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(customer1.id))

    }

    @Test
    fun `should get user forbidden`() {
        val customer = CustomerModel(name = " Marcone", email = "aa@aa.com", password = "12e1aca")
        mockMvc
            .perform(get("/0").with(user(UserCustomDetails(customer))))
            .andExpect(status().isForbidden)
            .andExpect(jsonPath("$.httpCode").value(403))
            .andExpect(jsonPath("$.message").value("Access dined"))

    }

}
