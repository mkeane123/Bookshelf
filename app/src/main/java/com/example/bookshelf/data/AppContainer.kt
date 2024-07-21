package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookShelfAllBooksRepository: AllBooksRepository
}

val jsonIgnoreUnkownKeys = Json{ ignoreUnknownKeys = true }

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(jsonIgnoreUnkownKeys.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    override val bookShelfAllBooksRepository: AllBooksRepository by lazy {
        NetoworkAllBooksRepository(retrofitService)
    }
}