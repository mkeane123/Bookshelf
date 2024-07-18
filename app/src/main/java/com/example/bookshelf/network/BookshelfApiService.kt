package com.example.bookshelf.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://www.googleapis.com/books/v1/"

//https://www.googleapis.com/books/v1/volumes?q=intitle:apple

// Retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/*
 * interface that defines how retrofit talks to web server using
 * HTTP requests
 */
interface BookshelfApiService {
    @GET("volumes?q=intitle:apple")// currently appends apple to end of base url, this will change
    suspend fun getBooks(): String
}

/*
 * Singleton object for Api can refer to this directly,
 * as there is only one
 */
object BookshelfApi {
    val retrofitService : BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }
}