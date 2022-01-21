package com.mercadolivro.mercadolivro.fundamentos

data class Pessoa(var name: String, var idade: Int) {
}

class Pessoa2(var name: String, var idade: Int) {
    override fun toString(): String {
        return "pessoa2: nome ${name}, Idade: ${idade}"
    }
}

fun main() {
    var pessoa = Pessoa("Marcone", 44);
    var pessoa2 = Pessoa2("Marcone", 44);
    println(pessoa);
    println(pessoa2)
}
