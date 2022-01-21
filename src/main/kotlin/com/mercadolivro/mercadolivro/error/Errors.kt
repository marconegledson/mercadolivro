package com.mercadolivro.mercadolivro.error

enum class Errors(var message: String) {

    ML000("Access denied"),
    ML001("Invalid request"),
    ML101("Book [%s] not exits"),
    ML102("Can't possible to change the status to [%s]"),
    ML201("Customer [%s] not exits")


}
