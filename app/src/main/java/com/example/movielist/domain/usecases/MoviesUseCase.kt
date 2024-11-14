package com.example.movielist.domain.usecases

import com.example.movielist.domain.dto.Movie
import com.example.movielist.data.remote.NetworkError
import com.example.movielist.data.remote.Result
import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.utils.SortOrder
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    suspend fun getMovies(
        releaseYear: Int?,
        sortBy: SortOrder?,
        page: Int?,
        includeAdult: Boolean?,
        includeVideo: Boolean?,
        language: String?,
        region: String?,
    ): Result<List<Movie>, NetworkError>

    suspend fun insertMovies(movie: MovieDetailsEntity): Long
    suspend fun removeMovie(movie: MovieDetailsEntity): Int
    fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>>
}