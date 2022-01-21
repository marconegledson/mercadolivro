package com.mercadolivro.mercadolivro.exception

import java.lang.Exception

class InvalidStatusException(override var message: String, val errorCode: String): Exception() {
}
