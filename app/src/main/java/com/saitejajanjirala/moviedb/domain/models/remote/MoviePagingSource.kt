package com.saitejajanjirala.moviedb.domain.models.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saitejajanjirala.moviedb.data.remote.ApiService

class MoviePagingSource(
    private val apiService: ApiService,
    private val endpoint: suspend (Int) -> retrofit2.Response<MovieResult>
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1 // Default to the first page
        return try {
            val response = endpoint(page)
            if (response.isSuccessful) {
                val body = response.body()
                val movies = body?.results.orEmpty() // Get the results or an empty list

                LoadResult.Page(
                    data = movies,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page >= (body?.totalPages ?: page)) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
