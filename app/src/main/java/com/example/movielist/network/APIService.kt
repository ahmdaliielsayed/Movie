package com.example.movielist.network

import com.example.movielist.listofmovies.data.dto.MovieResponse
import com.example.movielist.utils.SortOrder
import com.example.movielist.utils.Constants.EMPTY
import com.example.movielist.utils.Constants.LOCALE_EN_US
import com.example.movielist.utils.Constants.ONE
import com.example.movielist.utils.Constants.YEAR_2024
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("primary_release_year") releaseYear: Int? = YEAR_2024,
        @Query("sort_by") sortBy: SortOrder? = SortOrder.VOTE_AVERAGE_DESC,
        @Query("page") page: Int? = ONE,
        @Query("include_adult") includeAdult: Boolean? = false,
        @Query("include_video") includeVideo: Boolean? = false,
        @Query("language") language: String? = LOCALE_EN_US,
        @Query("region") region: String? = EMPTY,
    ): Response<MovieResponse>
}