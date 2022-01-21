package com.mercadolivro.mercadolivro.model

import com.mercadolivro.mercadolivro.error.Errors
import com.mercadolivro.mercadolivro.exception.InvalidStatusException
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field:NotEmpty(message = "O nome nao pode ser vazio")
    @Column(name = "name")
    var name: String,

    @field:NotNull(message = "E-mail deve ser valido")
    @Column(name = "price")
    var price: BigDecimal,

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var customerModel: CustomerModel?

) {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status = Status.ATIVO
        set(value) {
            if (field == Status.CANCELADO || field == Status.REMOVIDO && value == Status.ATIVO)
                throw InvalidStatusException(Errors.ML201.name,  Errors.ML201.message.format(field))
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customerModel: CustomerModel?,
        status: Status?
    ) : this(id, name, price, customerModel) {
        this.status = status ?: Status.ATIVO
    }

}
