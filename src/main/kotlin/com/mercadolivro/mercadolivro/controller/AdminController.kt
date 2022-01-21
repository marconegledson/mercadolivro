package com.mercadolivro.mercadolivro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController {

    @GetMapping("/report")
    fun report(): String {
        return "This is a report. Only admins can see it."
    }


}
