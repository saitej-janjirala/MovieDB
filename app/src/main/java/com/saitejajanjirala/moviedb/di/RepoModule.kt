package com.saitejajanjirala.moviedb.di

import com.saitejajanjirala.moviedb.data.repo.MovieRepoImpl
import com.saitejajanjirala.moviedb.data.repo.PagingMoviesRepoImpl
import com.saitejajanjirala.moviedb.domain.repo.MovieRepo
import com.saitejajanjirala.moviedb.domain.repo.PagingMoviesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindMovieRepo(movieRepoImpl: MovieRepoImpl): MovieRepo

    @Binds
    abstract fun bindMoviePagingRepo(pagingMoviesRepoImpl: PagingMoviesRepoImpl):PagingMoviesRepo

}