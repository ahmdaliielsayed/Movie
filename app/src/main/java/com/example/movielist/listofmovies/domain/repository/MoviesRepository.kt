package com.example.movielist.listofmovies.domain.repository

import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.domain.dto.MovieResponse
import com.example.movielist.utils.SortOrder
import kotlinx.coroutines.flow.Flow
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

    suspend fun insertMovies(movie: MovieDetailsEntity): Long
    suspend fun removeMovie(movie: MovieDetailsEntity): Int
    fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>>
}