package com.saitejajanjirala.moviedb.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result

@Composable
fun HorizontalMovies(
    modifier: Modifier,
    state: Result<List<Movie>>,
    tag: String,
    onMovieClicked: (movie: Movie) -> Unit,
    onMoreClicked:(tag:String)->Unit
) {
    Column(modifier = modifier.padding(8.dp)) {
        HeadingWithMoreText(tag) {
            onMoreClicked(tag)
        }
        Spacer(Modifier.height(8.dp))
        when (state) {
            is Result.Error -> {
                Text(state.m ?: "Something went wrong")
            }
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is Result.Success -> {
                state.d?.let {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(it) { movie ->
                            MovieCardRow(movie,onMovieClicked)
                        }
                    }
                }
            }
            else -> {}
        }
    }
}