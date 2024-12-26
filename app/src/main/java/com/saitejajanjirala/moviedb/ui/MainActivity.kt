package com.saitejajanjirala.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result
import com.saitejajanjirala.moviedb.ui.detail.DetailScreen
import com.saitejajanjirala.moviedb.ui.home.HomeScreen
import com.saitejajanjirala.moviedb.ui.more.MoreMoviesScreen
import com.saitejajanjirala.moviedb.ui.theme.MovieDBTheme
import com.saitejajanjirala.moviedb.ui.utils.Screen
import com.saitejajanjirala.moviedb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), content = {paddingValues->
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.HomeScreen.route){
                        composable(Screen.HomeScreen.route){
                            HomeScreen(modifier = Modifier.padding(paddingValues), onMovieClicked = {
                                navigateToDetail(navController,it)
                            }, onMoreClicked = {
                                navigateToMoreScreen(it,navController)
                            })
                        }
                        composable(Screen.DetailScreen.route){
                            val movie = navController.previousBackStackEntry?.savedStateHandle?.get<Movie>(Util.MOVIE)
                            if(movie!=null){
                                DetailScreen(movie, onBackClicked = {
                                    navController.popBackStack()
                                })
                            }
                        }
                        composable(Screen.MoreMoviesScreen.route)
                        {
                            val tag = navController.previousBackStackEntry?.savedStateHandle?.get<String>(Util.TAG)
                            tag?.let {
                                MoreMoviesScreen(tag, onMovieClicked = {movie->
                                    navigateToDetail(navController,movie)
                                }, onBackClicked = {
                                  navController.popBackStack()
                                })
                            }
                        }
                    }
                })
            }
        }


    }




}

fun navigateToMoreScreen(tag: String, navController: NavHostController) {
    navController.currentBackStackEntry?.savedStateHandle?.set(Util.TAG,tag)
    navController.navigate(Screen.MoreMoviesScreen.route)
}


fun navigateToDetail(navController: NavHostController,movie: Movie){
    navController.currentBackStackEntry?.savedStateHandle?.set(Util.MOVIE,movie)
    navController.navigate(Screen.DetailScreen.route)
}

@Composable
private fun TrendingMovies(modifier: Modifier, state: Result<List<Movie>>) {
    when (state) {
        is Result.Error -> {
            Text(
                state.m ?: "Something went wrong",
                modifier = modifier.padding(16.dp)
            )
        }
        is Result.Loading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Result.Success -> {
            state.d?.let {
//                LazyColumn(modifier = modifier) {
//                    items(it) { movie ->
//                        MovieCard(movie, {
//
//                        })
//                    }
//                }
            }
        }
        else -> {}
    }
}


@Composable
fun MovieCard(movie: Movie, onMovieClicked: (movie: Movie) -> Unit){
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(8.dp).background(Color.White).clickable {
            onMovieClicked(movie)
        }
    ) {
        Row {
            AsyncImage(
                model = "${Util.IMAGE_PATH}${movie.posterPath}",
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp).width(150.dp)
            )
            Column(Modifier.padding(12.dp)) {
                Text(text = movie.title ?: "Title", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = movie.overview ?: "Overview",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Release Date: ${movie.releaseDate ?: "N/A"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(4.dp))
            }

        }
    }
}

