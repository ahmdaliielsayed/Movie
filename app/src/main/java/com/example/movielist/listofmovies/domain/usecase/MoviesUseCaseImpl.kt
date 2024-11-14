package com.example.movielist.listofmovies.domain.usecase

import com.example.movielist.listofmovies.data.dto.Movie
import com.example.movielist.listofmovies.data.dto.MovieResponse
import com.example.movielist.network.safeCall
import com.example.movielist.listofmovies.domain.repository.MoviesRepository
import com.example.movielist.listofmovies.domain.utils.NetworkError
import com.example.movielist.listofmovies.domain.utils.Result
import com.example.movielist.listofmovies.domain.utils.map
import com.example.movielist.utils.SortOrder

class MoviesUseCaseImpl(
    private val moviesRepository: MoviesRepository
): MoviesUseCase {

    override suspend fun getMovies(
        releaseYear: Int?,
        sortBy: SortOrder?,
        page: Int?,
        includeAdult: Boolean?,
        includeVideo: Boolean?,
        language: String?,
        region: String?
    ): Result<List<Movie>, NetworkError> {
        return safeCall<MovieResponse> {
            moviesRepository.getMovies(releaseYear, sortBy, page, includeAdult, includeVideo, language, region)
        }.map { response ->
            response.moviesList?.filterNotNull() ?: emptyList()
        }
    }
}