package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit,
    contentPaddingValues: PaddingValues,
    bookshelfViewModel: BookshelfViewModel
){

    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is BookshelfUiState.Success -> BookshelfScreen(data = "Poopy", modifier = Modifier.fillMaxSize())
        is BookshelfUiState.Error -> ErrorScreen({},modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier
){

}

@Composable
fun BookshelfScreen(
    data: String,
    modifier: Modifier
    )
{

}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier
){

}

