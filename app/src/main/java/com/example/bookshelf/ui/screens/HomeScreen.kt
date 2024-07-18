package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit,
    contentPaddingValues: PaddingValues,
    bookshelfViewModel: BookshelfViewModel
){

    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is BookshelfUiState.Success -> BookshelfScreen(data = bookshelfUiState.books, modifier = Modifier.fillMaxSize())
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
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())

    ) {
        Text(text = data, color = Color.White, )
    }


}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier
){

}

