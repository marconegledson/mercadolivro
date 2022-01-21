package com.mercadolivro.mercadolivro.config

import com.mercadolivro.mercadolivro.error.ErrorResponse
import com.mercadolivro.mercadolivro.error.Errors
import com.mercadolivro.mercadolivro.error.FieldErrorResponse
import com.mercadolivro.mercadolivro.exception.InvalidStatusException
import com.mercadolivro.mercadolivro.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleException(e: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error =  ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = e.message,
            internalCode = e.errorCode,
            errors = null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InvalidStatusException::class)
    fun handleException(e: InvalidStatusException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error =  ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.message,
            internalCode = e.errorCode,
            errors = null
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(e: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error =  ErrorResponse(
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            message = e.message.substring(1, 100) + "...",
            internalCode = Errors.ML001.name,
            errors = e.bindingResult.fieldErrors.map{ FieldErrorResponse( it.defaultMessage ?: "invalid field", it.field) }
        )
        return ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleException(e: AccessDeniedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = e.message?.let {
            ErrorResponse(
                status = HttpStatus.FORBIDDEN.value(),
                message = it,
                internalCode = Errors.ML000.name,
                errors = null
            )
        }
        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }
}
