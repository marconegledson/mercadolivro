package com.mercadolivro.mercadolivro.controller

import com.mercadolivro.mercadolivro.model.PurchaseModel
import com.mercadolivro.mercadolivro.service.PurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchase")
class PurchaseController {

    @Autowired
    val purchaseService: PurchaseService? = null

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PurchaseModel) {
        purchaseService!!.create(request)
    }

}
