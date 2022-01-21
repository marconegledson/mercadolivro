package com.mercadolivro.mercadolivro.fundamentos

fun main() {
    var list = listOf<Int>(1, 2, 3)
    var setNumeros = setOf<Int>(1, 2, 3)
    var setNumerosSt = mutableSetOf<Int>(1, 2, 3)
    var listMu = mutableListOf<Int>(1, 2, 3)
    var map = mapOf("mac" to 24, "pp" to 25)
    var map2 = mutableMapOf<String, Int>("mac" to 24, "pp" to 25)


    var pares = list.filter { it % 2 == 0 }

    map2.put("mac", 25)
    map2["mac"] = 25

    list.forEach {
        println(it)
    }

    for (numero in list){
        println(numero)
    }
    println(list[0])
    println(list.get(0))

    listMu.add(2)

    listMu[0] = 4

    listMu.sort()

    println(listMu)


    println(pares)
}
