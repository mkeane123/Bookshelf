package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.network.classes.AllBooks

@Composable
fun HomeScreen(
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit,
    contentPaddingValues: PaddingValues,
    bookshelfViewModel: BookshelfViewModel
){

    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is BookshelfUiState.Success -> BookshelfScreen(allBooks = bookshelfUiState.allBooks, modifier = Modifier.fillMaxSize())
        is BookshelfUiState.Error -> ErrorScreen(retryAction,modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier
){
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = "Loading", // move to strings
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun BookshelfScreen(
    allBooks: AllBooks,
    modifier: Modifier
    )
{
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())

    ) {
        //Text(text = allBooks.items[0].volumeInfo.title)
        for (i in 0..9) {
            Text(text = allBooks.items[i].volumeInfo.title)
        }
        /*
         * Extract book id's from this list then get the books using the ids
         *
         */
        val photo = allBooks.items[0].volumeInfo.imageLinks.smallThumbnail
        val photoToShow = photo.replace("http", "https")
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photoToShow)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }


}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Loading failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}

