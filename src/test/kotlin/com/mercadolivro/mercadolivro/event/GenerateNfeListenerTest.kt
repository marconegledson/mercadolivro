package com.mercadolivro.mercadolivro.event

import com.mercadolivro.mercadolivro.model.PurchaseModel
import com.mercadolivro.mercadolivro.service.PurchaseService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockKExtension::class)
class GenerateNfeListenerTest {

    @InjectMockKs
    private lateinit var generateNfeListener: GenerateNfeListener

    @MockK
    private lateinit var purchaseService: PurchaseService

    @Test
    fun `should listen`() {
        val nfe = UUID.randomUUID()
        var purchaseModel = PurchaseModel(price = BigDecimal.TEN, createAt = LocalDateTime.now())
        var purchaseCp = purchaseModel.copy(nfe = nfe.toString())
        mockkStatic(UUID::class)

        every { UUID.randomUUID() } returns nfe
        every { purchaseService.create(purchaseCp) } just runs

        generateNfeListener.listen(PurchaseEvent(this, purchaseModel))

        verify (exactly = 1) { purchaseService.create(purchaseCp) }

    }
}
