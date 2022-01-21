package com.mercadolivro.mercadolivro.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvaliableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvaliable(
    val message: String = "Email ja cadastrador",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)