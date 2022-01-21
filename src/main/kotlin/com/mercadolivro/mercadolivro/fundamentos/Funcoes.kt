package com.mercadolivro.mercadolivro.fundamentos

fun main() {
    dizOi(retornarNome(), 24)
    dizOi(idade = 24, nome = "Pedro")
    dizOi("Joao")
}

fun retornarNome(): String {
    return "Marcone"
}

fun dizOi(nome: String, idade: Int = 24) {
    println("Oi ${nome} parabens pelos seus ${idade} anos")
}
