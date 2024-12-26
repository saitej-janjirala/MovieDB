package com.saitejajanjirala.moviedb.util

object Util {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500/"
    const val TRENDING = "Trending Movies"
    const val NOW_PLAYING = "Now Playing Movies"
    const val UPCOMING = "Upcoming Movies"
    const val MOVIE = "movie"
    const val TAG = "tag"
    const val BEARER ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxN2I0YjAwYWM0ZTBhZDgzZWUxZWRjYTdiOWNiOTkwMCIsIm5iZiI6MTcyODIwMjYyNS40MzgwMDAyLCJzdWIiOiI2NzAyNDc4MWMzYzViM2ExYThmN2E0NjAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.tIwYJvlW0tYLw_XN4H5XzLq7Teugrj5ObQLmTbKV8kc"

    fun getImageUrl(urlPath : String?):String{
        return "${IMAGE_PATH}${urlPath}"
    }
}