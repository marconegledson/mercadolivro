package com.mercadolivro.mercadolivro.service

import com.mercadolivro.mercadolivro.event.PurchaseEvent
import com.mercadolivro.mercadolivro.model.PurchaseModel
import com.mercadolivro.mercadolivro.repository.PurchaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService {

    @Autowired
    private val purchaseRepository: PurchaseRepository? = null

    @Autowired
    private val applicationEventPublisher: ApplicationEventPublisher? = null

    fun create(purchaseModel: PurchaseModel) {
        purchaseModel.price = purchaseModel?.booksModel?.sumOf { it.price }!!
        purchaseRepository!!.save(purchaseModel)
        applicationEventPublisher!!.publishEvent(PurchaseEvent(this, purchaseModel))
    }

}
