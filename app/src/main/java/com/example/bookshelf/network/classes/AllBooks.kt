package com.example.bookshelf.network.classes

import kotlinx.serialization.Serializable



@Serializable
data class AllBooks(
    val kind: String,
    val totalItems: Int,
    val items: Array<Book>
)
