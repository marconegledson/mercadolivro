package com.mercadolivro.mercadolivro.fundamentos

fun main(){
    val x = 5
    when(x){
        5, -5 -> println("cinco")
        8 -> println("oito")
        10 -> {
            println("dez")
            println("uma dezena")
        }
        in 11 .. 15 -> println("x esta entre 11 e 15")
        !in 20 .. 25 -> println("x esta nao esta entre 20 e 25")
        else -> "nao sei"
    }

    when {
        comecaComOi(5) -> println("5")
        comecaComOi("oi tudo bem") -> println("tem oi")
    }
}

fun comecaComOi(x: Any): Boolean {
    return when (x) {
        is String -> x.startsWith("oi")
        else -> false
    }
}
