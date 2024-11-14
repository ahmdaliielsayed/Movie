package com.example.movielist.domain.usecases

import com.example.movielist.domain.dto.Movie
import com.example.movielist.data.remote.NetworkError
import com.example.movielist.data.remote.Result
import com.example.movielist.utils.SortOrder

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
}