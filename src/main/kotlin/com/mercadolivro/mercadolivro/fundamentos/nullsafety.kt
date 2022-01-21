package com.mercadolivro.mercadolivro.fundamentos

fun main(){
    var nome : String? = "Marcone"

    println(nome?.length)

    val toShort : Short = nome!!.length.toShort()

    var nome2: String? = "Marcone 2"
    var tamanho: Int = nome2?.length ?: 0

    var list : List<Int?> = listOf(1, 2, null, 4)
    var list2 : List<Int>? = null

    var pessoa: Pessoa? = Pessoa("Marcone", 23)

    println(pessoa!!.name)

}
