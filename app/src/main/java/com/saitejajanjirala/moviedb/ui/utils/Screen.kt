package com.saitejajanjirala.moviedb.ui.utils

sealed class Screen(val route : String) {
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")
    object MoreMoviesScreen : Screen("more_movies_screen")

}