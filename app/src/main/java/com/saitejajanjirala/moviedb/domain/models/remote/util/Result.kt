package com.saitejajanjirala.moviedb.domain.models.remote.util

sealed class Result<T>(val d : T?=null, val m : String?=null,val isLoading: Boolean = false){
    data class Success<T>(val data: T) : Result<T>(d=data)
    data class Error<T>(val message: String) : Result<T>(m=message)
    class Loading<T>(val loading: Boolean) : Result<T>(isLoading = loading)
}