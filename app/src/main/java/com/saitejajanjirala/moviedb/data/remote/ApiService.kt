package com.saitejajanjirala.moviedb.data.remote

import com.saitejajanjirala.moviedb.domain.models.remote.GenreResult
import com.saitejajanjirala.moviedb.domain.models.remote.MovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getTrendingMovies(@Query("page") page: Int = 1) : Response<MovieResult>

    @GET("movie/top_rated")
    suspend fun getNowPlaying(@Query("page") page: Int=1): Response<MovieResult>

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("page") page: Int=1): Response<MovieResult>

    @GET("genre/movie/list")
    suspend fun getGenreList():Response<GenreResult>
}