package com.example.movielist.moviedetails.domain

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    @PrimaryKey
    val id: Int?,
    val originalLanguage: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?
): Parcelable
