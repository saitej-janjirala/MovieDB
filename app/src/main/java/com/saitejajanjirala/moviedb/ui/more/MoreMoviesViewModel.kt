package com.saitejajanjirala.moviedb.ui.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saitejajanjirala.moviedb.domain.models.remote.Movie
import com.saitejajanjirala.moviedb.domain.repo.PagingMoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreMoviesViewModel @Inject constructor(private val pagingMoviesRepo: PagingMoviesRepo): ViewModel(){


    fun getMovies(tag: String) = pagingMoviesRepo.getMoviesWithPaging(tag)
        .cachedIn(viewModelScope)
}