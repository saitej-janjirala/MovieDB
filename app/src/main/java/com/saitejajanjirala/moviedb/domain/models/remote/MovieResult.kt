package com.saitejajanjirala.moviedb.domain.models.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResult(
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "results")
    val results: List<Movie>? = null,
    @Json(name = "total_pages")
    val totalPages: Int? = null,
    @Json(name = "total_results")
    val totalResults: Int? = null
)