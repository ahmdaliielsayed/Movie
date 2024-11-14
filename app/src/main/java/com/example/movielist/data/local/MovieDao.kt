package com.example.movielist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movielist.domain.dto.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDetailsEntity): Long

    @Delete
    suspend fun removeMovie(movie: MovieDetailsEntity): Int

    @Query("SELECT * FROM movies")
    fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>>
}