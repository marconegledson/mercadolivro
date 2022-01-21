package com.mercadolivro.mercadolivro.repository

import com.mercadolivro.mercadolivro.model.BookModel
import com.mercadolivro.mercadolivro.model.Status
import org.hibernate.annotations.SQLDelete
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@SQLDelete(sql = "status = 'REMOVIDO'")
interface BookRepository : JpaRepository<BookModel, Int>{

    fun findByStatus(status: Status, pageable: Pageable): Page<BookModel>
}
