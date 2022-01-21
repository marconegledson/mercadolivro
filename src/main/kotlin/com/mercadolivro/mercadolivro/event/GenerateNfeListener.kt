package com.mercadolivro.mercadolivro.event

import com.mercadolivro.mercadolivro.service.PurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener {

    @Autowired
    private var purchaseService: PurchaseService? = null

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)
        purchaseService!!.create(purchaseModel)
    }
}
