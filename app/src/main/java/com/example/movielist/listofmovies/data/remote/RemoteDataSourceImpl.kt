package com.example.movielist.listofmovies.data.remote

import com.example.movielist.domain.dto.MovieResponse
import com.example.movielist.data.remote.APIService
import com.example.movielist.utils.SortOrder
import retrofit2.Response

class RemoteDataSourceImpl(
    private val apiService: APIService
) : RemoteDataSource {

    override suspend fun getMovies(
        releaseYear: Int?,
        sortBy: SortOrder?,
        page: Int?,
        includeAdult: Boolean?,
        includeVideo: Boolean?,
        language: String?,
        region: String?
    ): Response<MovieResponse> {
        return apiService.getMovies(
            releaseYear,
            sortBy,
            page,
            includeAdult,
            includeVideo,
            language,
            region
        )
    }
}