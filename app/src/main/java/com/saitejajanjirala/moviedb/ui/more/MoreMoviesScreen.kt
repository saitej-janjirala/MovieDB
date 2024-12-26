package com.saitejajanjirala.moviedb.ui.more

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.ui.MovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreMoviesScreen(
     tag : String,
     onMovieClicked : (movie:Movie)->Unit,
     onBackClicked :()->Unit,
     viewModel: MoreMoviesViewModel = hiltViewModel()
){


    val movies = viewModel.getMovies(tag).collectAsLazyPagingItems()
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = tag,
                        maxLines = 1,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClicked()

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },

            )
        },

        ){
        LazyColumn(Modifier.padding(it)) {
            items(movies.itemCount) { i ->
                movies[i]?.let {
                    MovieCard(it,onMovieClicked)
                }

            }
            movies.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }
                    loadState.append is LoadState.Error -> {
                        item { Text("Error loading more movies") }
                    }
                }
            }
        }
    }




}