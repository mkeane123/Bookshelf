package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.AllBooksRepository
import com.example.bookshelf.data.NetoworkAllBooksRepository
import com.example.bookshelf.network.classes.AllBooks

import com.example.bookshelf.network.BookshelfApiService
import kotlinx.coroutines.launch
import java.io.IOException

/*
 * Different states the uiState can be in
 */
sealed interface BookshelfUiState {
    data class Success(val allBooks: AllBooks) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}

class BookshelfViewModel(
    private val allBooksRepository: AllBooksRepository
) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)//
    private set // makes default setter private, so cant be changed outside

    init {
        getAllBooks()
    }

    fun getAllBooks() { // will get one array of all the books then need anohter fnction to get individual books
        // call the API function to get the books from web
        viewModelScope.launch {// launches a coroutine, so web request wont block main thread
            bookshelfUiState = try {
                //val listResult = BookshelfApi.retrofitService.getAllBooks()//call the functionon the bookshelfApi singleton

                val listResult = allBooksRepository.getAllBooks()

                BookshelfUiState.Success(listResult)
                // Now code to get individual books from listResult, seperate GET request
                /*
                 * Get book id's from list result and store in a mutable list
                 * Use this list to make multiple seperate requests to server for individual books
                 */
            } catch (e: IOException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val allBooksRepository = application.container.bookShelfAllBooksRepository
                BookshelfViewModel(allBooksRepository = allBooksRepository)
            }
        }
    }



}

