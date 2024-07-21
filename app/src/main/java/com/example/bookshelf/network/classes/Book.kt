package com.example.bookshelf.network.classes

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

@Serializable
data class Book(
    val volumeInfo: VolumeInfo
)


/*
@Serializable
data class Book(
    val kind: String,
    val id: String,
    val etag: String
)
 */