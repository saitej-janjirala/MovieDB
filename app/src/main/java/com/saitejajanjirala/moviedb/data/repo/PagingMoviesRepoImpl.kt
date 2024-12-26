package com.saitejajanjirala.moviedb.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.saitejajanjirala.moviedb.data.remote.ApiService
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.models.remote.MoviePagingSource
import com.saitejajanjirala.moviedb.domain.models.remote.util.Result
import com.saitejajanjirala.moviedb.domain.repo.PagingMoviesRepo
import com.saitejajanjirala.moviedb.util.Util
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingMoviesRepoImpl @Inject constructor(private val apiService: ApiService) : PagingMoviesRepo {

    override  fun getMoviesWithPaging(tag: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService){page->
                    when (tag) {
                        Util.TRENDING -> apiService.getTrendingMovies(page)
                        Util.NOW_PLAYING -> apiService.getNowPlaying(page)
                        Util.UPCOMING -> apiService.getUpcoming(page)
                        else -> throw IllegalArgumentException("Unknown tag: $tag")
                    }
                }
            }
        ).flow
    }


}