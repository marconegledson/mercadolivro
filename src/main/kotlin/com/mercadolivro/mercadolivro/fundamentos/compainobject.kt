package com.mercadolivro.mercadolivro.fundamentos

class MinhaClasse {
    companion object {
        fun criarClasse() : MinhaClasse {
            return MinhaClasse()
        }
    }

}

class SegundaClasse {
    fun criarClasse() : SegundaClasse {
        return SegundaClasse()
    }
}

fun main() {
    var segundaClasse = SegundaClasse().criarClasse()
    var minhaClasse = MinhaClasse.criarClasse()
}
