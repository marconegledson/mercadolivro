package com.mercadolivro.mercadolivro.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMINISTRATOR') || #id == autentication.principal.id")
annotation class OnlyAccessPermission()
