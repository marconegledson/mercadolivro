package com.mercadolivro.mercadolivro.model

import com.mercadolivro.mercadolivro.validation.EmailAvaliable
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field:NotEmpty
    @Column(name = "name")
    var name: String,

    @field:Email
    @field:EmailAvaliable
    @Column(name = "email")
    var email: String,

    @field:NotEmpty
    @Column(name = "password")
    var password: String,

    @field:NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status = Status.ATIVO,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    var roles: Set<Role> = setOf()


)
