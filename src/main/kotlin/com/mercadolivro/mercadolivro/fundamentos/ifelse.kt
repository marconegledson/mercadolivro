package com.mercadolivro.mercadolivro.fundamentos

fun main(){
    println(parImpar(3))
}


fun parImpar(numero: Int): String {
    return if(numero % 2 == 0){
        "par"
    }else {
        "impar"
    }
}
