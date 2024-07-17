package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/*
 * Different states the uiState can be in
 */
sealed interface BookshelfUiState {
    data class Success(val books: String /*List<Book*/) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}

class BookshelfViewModel(/*pass in repository here eventually*/) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)//
    private set // makes default setter private, so cant be changed outside

    init {
        getBooks()
    }

    fun getBooks(){
        // call the API function to get the books from web
    }



}

