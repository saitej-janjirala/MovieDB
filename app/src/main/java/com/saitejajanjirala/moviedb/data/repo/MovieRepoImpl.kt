package com.saitejajanjirala.moviedb.data.repo

import com.saitejajanjirala.moviedb.data.remote.ApiService
import com.saitejajanjirala.moviedb.domain.models.remote.Genre
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.repo.MovieRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepoImpl @Inject constructor(private val apiService: ApiService) : MovieRepo {

    override suspend fun getTrendingMovies(): Flow<Result<List<Movie>>> = flow{
        emit(Result.Loading(true))
        val response = apiService.getTrendingMovies()
        emit(Result.Loading(false))
        when{
            response.isSuccessful -> {
                response.body()?.let {
                   it?.results?.let {movies->
                       emit(Result.Success(movies))
                   }?:emit(Result.Error("No Results found"))
                } ?: emit(Result.Error("Something went wrong"))
            }
            else->{
                emit(Result.Error(response.message()))
            }
        }
    }.catch {
        emit(Result.Error(it.message.toString()))
    }

    override suspend fun getNowPlayingMovies(): Flow<Result<List<Movie>>> = flow{
        emit(Result.Loading(true))
        val response = apiService.getNowPlaying()
        emit(Result.Loading(false))
        when{
            response.isSuccessful -> {
                response.body()?.let {
                    it?.results?.let {movies->
                        emit(Result.Success(movies))
                    }?:emit(Result.Error("No Results found"))
                } ?: emit(Result.Error("Something went wrong"))
            }
            else->{
                emit(Result.Error(response.message()))
            }
        }
    }.catch {
        emit(Result.Error(it.message.toString()))
    }

    override suspend fun getUpComingMovies(): Flow<Result<List<Movie>>> = flow{
        emit(Result.Loading(true))
        val response = apiService.getUpcoming()
        emit(Result.Loading(false))
        when{
            response.isSuccessful -> {
                response.body()?.let {
                    it?.results?.let {movies->
                        emit(Result.Success(movies))
                    }?:emit(Result.Error("No Results found"))
                } ?: emit(Result.Error("Something went wrong"))
            }
            else->{
                emit(Result.Error(response.message()))
            }
        }
    }.catch {
        emit(Result.Error(it.message.toString()))
    }

    override suspend fun getGenreList(): Flow<Result<List<Genre>>> =flow{
        emit(Result.Loading(true))
        val response = apiService.getGenreList()
        emit(Result.Loading(false))
        when{
            response.isSuccessful -> {
                response.body()?.let {
                    it.genres?.let {genres->
                        emit(Result.Success(genres))
                    }?:emit(Result.Error("No Results found"))
                } ?: emit(Result.Error("Something went wrong"))
            }
            else->{
                emit(Result.Error(response.message()))
            }
        }
    }.catch {
        emit(Result.Error(it.message.toString()))
    }
}