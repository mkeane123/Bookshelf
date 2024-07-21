package com.example.bookshelf.network.classes

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks
    // extract the id
)