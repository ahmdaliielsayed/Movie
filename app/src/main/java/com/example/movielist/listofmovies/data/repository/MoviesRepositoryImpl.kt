package com.example.movielist.listofmovies.data.repository

import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.domain.dto.MovieResponse
import com.example.movielist.listofmovies.data.local.LocalDataSource
import com.example.movielist.listofmovies.data.remote.RemoteDataSource
import com.example.movielist.listofmovies.domain.repository.MoviesRepository
import com.example.movielist.utils.SortOrder
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MoviesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
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

    override suspend fun insertMovies(movie: MovieDetailsEntity): Long {
        return localDataSource.insertMovies(movie)
    }

    override suspend fun removeMovie(movie: MovieDetailsEntity): Int {
        return localDataSource.removeMovie(movie)
    }

    override fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>> {
        return localDataSource.getAllFavoriteMovies()
    }
}