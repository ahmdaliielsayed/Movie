package com.example.movielist.listofmovies.domain.repository

import com.example.movielist.listofmovies.data.dto.MovieResponse
import com.example.movielist.utils.SortOrder
import retrofit2.Response

interface MoviesRepository {

    suspend fun getMovies(
        releaseYear: Int?,
        sortBy: SortOrder?,
        page: Int?,
        includeAdult: Boolean?,
        includeVideo: Boolean?,
        language: String?,
        region: String?,
    ): Response<MovieResponse>
}