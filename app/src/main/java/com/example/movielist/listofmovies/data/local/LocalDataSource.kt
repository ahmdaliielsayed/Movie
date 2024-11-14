package com.example.movielist.listofmovies.data.local

import com.example.movielist.domain.dto.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertMovies(movie: MovieDetailsEntity): Long
    suspend fun removeMovie(movie: MovieDetailsEntity): Int
    fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>>
}