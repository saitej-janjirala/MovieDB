package com.saitejajanjirala.moviedb.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.ui.MainViewModel
import com.saitejajanjirala.moviedb.util.Util
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
@Composable
fun DetailScreen(
    movie: Movie,
    onBackClicked: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    var backgroundColor by remember { mutableStateOf(Color.Black) }
    val context = LocalContext.current
    val genres = viewModel.getGenresByIds(movie.genreIds?: emptyList())

    LaunchedEffect(movie.backdropPath) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(Util.getImageUrl(movie.backdropPath))
            .allowHardware(false)
            .build()

        val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
        result?.toBitmap()?.let { bitmap ->
            Palette.from(bitmap).generate { palette ->
                val dominantColor = palette?.dominantSwatch?.rgb
                if (dominantColor != null) {
                    backgroundColor = Color(dominantColor)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box {
                    AsyncImage(
                        model = Util.getImageUrl(movie.backdropPath),
                        contentDescription = movie.originalTitle,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                    )
                    CircularIcon(onBackClicked = onBackClicked)
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = movie.title ?: "N/A",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Release Date: ${movie.releaseDate}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    Text(
                        text = "Popularity: ${movie.popularity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    Text(
                        text = "Vote Average: ${movie.voteAverage} (${movie.voteCount} votes)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    Text(
                        text = "Genres: ${genres.joinToString(", ")}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.overview ?: "N/A",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CircularIcon(onBackClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 28.dp, start = 8.dp)
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable { onBackClicked() },
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Sharp.KeyboardArrowLeft,
            contentDescription = "Go Back",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}
