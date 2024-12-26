package com.saitejajanjirala.moviedb.domain.repo

import androidx.paging.PagingData
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import kotlinx.coroutines.flow.Flow

interface PagingMoviesRepo {
    fun getMoviesWithPaging(tag:String): Flow<PagingData<Movie>>
}