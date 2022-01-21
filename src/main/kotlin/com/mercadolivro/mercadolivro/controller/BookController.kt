package com.mercadolivro.mercadolivro.controller

import com.mercadolivro.mercadolivro.model.BookModel
import com.mercadolivro.mercadolivro.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("book")
class BookController {

    @Autowired
    private val bookService: BookService? = null

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody book: BookModel): BookModel? {
        return bookService!!.create(book)
    }

    @GetMapping
    fun books(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookModel> {
        return bookService!!.books(pageable);
    }

    @GetMapping("/active")
    fun booksActive(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookModel> {
       return bookService!!.activities(pageable)
    }

    @GetMapping("/{id}")
    fun book(@PathVariable id: Int): BookModel? = bookService!!.book(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService!!.delete(id)
    }

}
