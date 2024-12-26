package com.saitejajanjirala.moviedb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MovieRepo): ViewModel() {
    private val _trendingMovies = MutableStateFlow<Result<List<Movie>>>(Result.Loading(false))
    val trendingMovies : StateFlow<Result<List<Movie>> > = _trendingMovies
    private val _nowPlaying = MutableStateFlow<Result<List<Movie>>>(Result.Loading(false))
    val nowPlaying : StateFlow<Result<List<Movie>> > = _nowPlaying

    private val _upComing = MutableStateFlow<Result<List<Movie>>>(Result.Loading(false))
    val upComing : StateFlow<Result<List<Movie>> > = _upComing
    private var map = mutableMapOf<Int,String>()
    init {
        viewModelScope.launch {
            getTrendingMovies()
            getNowPlayingMovies()
            getUpComingMovies()
            getGenres()
        }
    }

    private suspend fun getGenres() {
        repo.getGenreList().collect{
            if(it is Result.Success){
                val data = it.data
                map.clear()
                for((id,genre) in data){
                    map[id] = genre
                }
                println(map.entries)
            }
        }
    }

    private suspend fun getUpComingMovies() {
        repo.getUpComingMovies().collect{
            _upComing.value = it
        }
    }

    private suspend fun getNowPlayingMovies() {
        repo.getNowPlayingMovies().collect{
            _nowPlaying.value = it
        }
    }

    private suspend fun getTrendingMovies(){
        repo.getTrendingMovies().collect{
            _trendingMovies.value = it
        }
    }

    fun getGenresByIds(ids:List<Int>):List<String>{
       return ids.mapNotNull {id-> map[id] }
    }
}