package com.example.movielist.listofmovies.domain.usecase

import com.example.movielist.listofmovies.data.dto.Movie
import com.example.movielist.listofmovies.domain.utils.NetworkError
import com.example.movielist.listofmovies.domain.utils.Result
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