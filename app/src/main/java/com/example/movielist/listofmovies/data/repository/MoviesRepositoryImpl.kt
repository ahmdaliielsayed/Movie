package com.example.movielist.listofmovies.data.repository

import com.example.movielist.domain.dto.MovieResponse
import com.example.movielist.listofmovies.data.remote.RemoteDataSource
import com.example.movielist.listofmovies.domain.repository.MoviesRepository
import com.example.movielist.utils.SortOrder
import retrofit2.Response

class MoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MoviesRepository {

    override suspend fun getMovies(
        releaseYear: Int?,
        sortBy: SortOrder?,
        page: Int?,
        includeAdult: Boolean?,
        includeVideo: Boolean?,
        language: String?,
        region: String?
    ): Response<MovieResponse> {

        return remoteDataSource.getMovies(releaseYear, sortBy, page, includeAdult, includeVideo, language, region)
    }

}