package com.mercadolivro.mercadolivro.service

import com.mercadolivro.mercadolivro.error.Errors
import com.mercadolivro.mercadolivro.exception.NotFoundException
import com.mercadolivro.mercadolivro.model.BookModel
import com.mercadolivro.mercadolivro.model.Status
import com.mercadolivro.mercadolivro.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService {

    @Autowired
    private val customerService: CustomerService? = null

    @Autowired
    private val bookRepository: BookRepository? = null


    fun create(book: BookModel): BookModel? {
        val customer = customerService!!.create(customerService!!.customer(book.customerModel?.id!!))
        book.customerModel = customer
        return bookRepository!!.save(book);
    }

    fun books(pageable: Pageable): Page<BookModel> {
        return bookRepository!!.findAll(pageable);
    }

    fun activities(pageable: Pageable): Page<BookModel> {
        return bookRepository!!.findByStatus(Status.ATIVO, pageable)
    }

    fun book(id: Int): BookModel? = bookRepository!!.findById(id).orElseThrow { NotFoundException(Errors.ML101.message.format(id), Errors.ML101.name) }

    fun delete(id: Int)  = bookRepository!!.deleteById(id)


}
