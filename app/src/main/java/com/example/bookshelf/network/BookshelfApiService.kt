package com.example.bookshelf.network

import com.example.bookshelf.network.classes.AllBooks
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

//https://www.googleapis.com/books/v1/volumes?q=intitle:apple

/*
 * interface that defines how retrofit talks to web server using
 * HTTP requests
 */
interface BookshelfApiService {
    @GET("volumes?q=intitle:apple")// currently appends apple to end of base url, this will change
    suspend fun getAllBooks(): AllBooks
}
