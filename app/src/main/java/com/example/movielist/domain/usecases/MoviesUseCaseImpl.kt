package com.example.movielist.domain.usecases

import com.example.movielist.domain.dto.Movie
import com.example.movielist.domain.dto.MovieResponse
import com.example.movielist.data.remote.safeCall
import com.example.movielist.listofmovies.domain.repository.MoviesRepository
import com.example.movielist.data.remote.NetworkError
import com.example.movielist.data.remote.Result
import com.example.movielist.data.remote.map
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