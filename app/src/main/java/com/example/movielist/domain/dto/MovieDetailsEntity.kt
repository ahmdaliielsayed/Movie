package com.example.movielist.domain.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielist.utils.Constants.ONE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieDetailsEntity(
    @PrimaryKey
    val id: Int? = ONE,
    val originalLanguage: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    var isFavorite: Boolean = false
): Parcelable
