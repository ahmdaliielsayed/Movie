package com.example.movielist.domain.dto

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int?,
    @SerializedName("results")
    val moviesList: List<Movie?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)