package com.saitejajanjirala.moviedb.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.util.Util

@Composable
fun MovieCardRow(movie: Movie, onMovieClicked: (movie: Movie) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp).background(Color.White).clickable {
            onMovieClicked(movie)
        }
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            AsyncImage(
                model = "${Util.IMAGE_PATH}${movie.posterPath}",
                contentDescription = "Movie Poster",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(200.dp).width(150.dp)
            )
        }
    }
}