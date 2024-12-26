package com.saitejajanjirala.moviedb.domain.repo

import com.saitejajanjirala.moviedb.domain.models.remote.Genre
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import kotlinx.coroutines.flow.Flow
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result


interface MovieRepo {
    suspend fun getTrendingMovies(): Flow<Result<List<Movie>>>
    suspend fun getNowPlayingMovies(): Flow<Result<List<Movie>>>
    suspend fun getUpComingMovies(): Flow<Result<List<Movie>>>
    suspend fun getGenreList():Flow<Result<List<Genre>>>
}