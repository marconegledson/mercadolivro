package com.mercadolivro.mercadolivro.exception

import java.lang.Exception

class AutenticationException(override var message: String, val errorCode: String): Exception() {
}
