package com.mercadolivro.mercadolivro.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "purchase")
data class PurchaseModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "nfe")
    val nfe: String? = null,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "create_at")
    val createAt: LocalDateTime = LocalDateTime.now(),

    @field:NotNull
    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var customerModel: CustomerModel? = null,

    @JoinTable(name = "purchase_book", joinColumns = [JoinColumn(name = "purchase_id")], inverseJoinColumns =  [JoinColumn(name = "book_id")])
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var booksModel: List<BookModel>? = null

)
