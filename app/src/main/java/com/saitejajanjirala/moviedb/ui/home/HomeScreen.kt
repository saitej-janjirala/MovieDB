package com.saitejajanjirala.moviedb.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result
import com.saitejajanjirala.moviedb.ui.MainViewModel
import com.saitejajanjirala.moviedb.ui.MovieCard
import com.saitejajanjirala.moviedb.ui.utils.HeadingWithMoreText
import com.saitejajanjirala.moviedb.ui.utils.HorizontalMovies
import com.saitejajanjirala.moviedb.util.Util

@Composable
fun HomeScreen(
    modifier: Modifier,
    onMovieClicked:(movie:Movie)->Unit,
    onMoreClicked:(tag:String)->Unit,
    viewModel: MainViewModel = hiltViewModel()
){
    val nowPlaying by viewModel.nowPlaying.collectAsState()
    val upComing by viewModel.upComing.collectAsState()
    val trendingMovies by viewModel.trendingMovies.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        // Section: Now Playing Movies
        item {
            HorizontalMovies(
                modifier = Modifier.fillMaxWidth(),
                state = nowPlaying,
                tag = Util.NOW_PLAYING,
                onMovieClicked
            ){
                onMoreClicked(Util.NOW_PLAYING)
            }
        }
        // Section: Upcoming Movies
        item {
            HorizontalMovies(
                modifier = Modifier.fillMaxWidth(),
                state = upComing,
                tag = Util.UPCOMING,
                 onMovieClicked
            ){
                onMoreClicked(Util.UPCOMING)
            }
        }
        // Section: Trending Movies
        item {
            Column (Modifier.padding(8.dp)){
                HeadingWithMoreText(Util.TRENDING) {
                    onMoreClicked(Util.TRENDING)
                }
            }
        }
        trendingMovies.let { state ->
            when (state) {
                is Result.Error -> {
                    item {
                        Text(
                            text = state.m ?: "Something went wrong",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                is Result.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is Result.Success -> {
                    items(state.d ?: emptyList()) { movie ->
                        MovieCard(movie,onMovieClicked)
                    }
                }
                else -> {}
            }
        }
    }

}