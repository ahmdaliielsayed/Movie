package com.example.movielist.listofmovies.data.local

import com.example.movielist.data.local.MovieDao
import com.example.movielist.domain.dto.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val movieDao: MovieDao
): LocalDataSource {

    override suspend fun insertMovies(movie: MovieDetailsEntity): Long {
        return movieDao.insertMovie(movie)
    }

    override suspend fun removeMovie(movie: MovieDetailsEntity): Int {
        return movieDao.removeMovie(movie)
    }

    override fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>> {
        return movieDao.getAllFavoriteMovies()
    }
}