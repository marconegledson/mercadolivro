package com.mercadolivro.mercadolivro.fundamentos

fun main(){
    //printa1a10()
    printa(1, 2)
}

fun printa1a10() {
    for(numero in 1 .. 10) {
        println(numero)
    }
    for(numero in 10 downTo 1){
        println(numero)
    }
    for(numero in 2 .. 10 step 2){
        println(numero)
    }
}

fun printa(inicio: Int, fim: Int) {
    for(numero in inicio .. fim){
        println(numero)
    }

    var x = 0
    while (x < 10){
        println(x)
        x++
    }
}
