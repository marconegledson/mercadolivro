package com.mercadolivro.mercadolivro.event

import com.mercadolivro.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent
import org.springframework.stereotype.Component

@Component
class PurchaseEvent (
    source: Any,
    val purchaseModel: PurchaseModel
) : ApplicationEvent(source)
