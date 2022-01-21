package com.mercadolivro.mercadolivro.service

import com.mercadolivro.mercadolivro.event.PurchaseEvent
import com.mercadolivro.mercadolivro.model.PurchaseModel
import com.mercadolivro.mercadolivro.repository.PurchaseRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class PurchaseServiceTest {

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    private val purchaseEventSlot = slot<PurchaseEvent>()

    @Test
    fun `should create purchase`() {
        var purchaseModel = PurchaseModel(price = BigDecimal.TEN, createAt = LocalDateTime.now())

        every { purchaseRepository.save(any())} returns purchaseModel
        every { applicationEventPublisher.publishEvent(any())} just runs

        purchaseService.create(purchaseModel)

        verify(exactly = 1) { purchaseRepository.save(purchaseModel) }
        verify(exactly = 1) { applicationEventPublisher.publishEvent(capture(purchaseEventSlot)) }

        assertEquals(purchaseModel, purchaseEventSlot.captured.purchaseModel)

    }

}
