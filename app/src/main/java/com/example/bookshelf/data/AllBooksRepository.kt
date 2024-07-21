package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import com.example.bookshelf.network.classes.AllBooks

interface AllBooksRepository {
    suspend fun getAllBooks(): AllBooks
}

class NetoworkAllBooksRepository(
    private val bookshelfApiService: BookshelfApiService
) : AllBooksRepository {
    override suspend fun getAllBooks(): AllBooks = bookshelfApiService.getAllBooks()
}