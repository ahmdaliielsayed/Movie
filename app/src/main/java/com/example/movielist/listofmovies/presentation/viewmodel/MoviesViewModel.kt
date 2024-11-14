package com.example.movielist.listofmovies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.dto.Movie
import com.example.movielist.domain.usecases.MoviesUseCase
import com.example.movielist.data.remote.onError
import com.example.movielist.data.remote.onSuccess
import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.utils.Constants.NEGATIVE_ONE_LONG
import com.example.movielist.utils.Constants.ZERO
import com.example.movielist.utils.SortOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> = _moviesList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isInsertedSuccessfully = MutableStateFlow<Boolean?>(null)
    val isInsertedSuccessfully: StateFlow<Boolean?> = _isInsertedSuccessfully

    private val _isDeletedSuccessfully = MutableStateFlow<Boolean?>(null)
    val isDeletedSuccessfully: StateFlow<Boolean?> = _isDeletedSuccessfully

    private val _favoriteMovies = MutableStateFlow<List<MovieDetailsEntity>>(emptyList())

    init {
        viewModelScope.launch {
            moviesUseCase.getAllFavoriteMovies().collect { favoriteMovies ->
                _favoriteMovies.value = favoriteMovies
                updateMoviesListWithFavorites()
            }
        }
    }

    fun getMovies(
        releaseYear: Int? = null,
        sortBy: SortOrder? = null,
        page: Int? = null,
        includeAdult: Boolean? = null,
        includeVideo: Boolean? = null,
        language: String? = null,
        region: String? = null,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            moviesUseCase.getMovies(
                releaseYear,
                sortBy,
                page,
                includeAdult,
                includeVideo,
                language,
                region
            ).onSuccess {
                _isLoading.value = false
                _moviesList.value = it
                _errorMessage.value = null
                updateMoviesListWithFavorites()
            }.onError {
                _isLoading.value = false
                _errorMessage.value = it.name
            }
        }
    }

    private fun updateMoviesListWithFavorites() {
        _moviesList.value = _moviesList.value.map { movie ->
            val isFavorite = _favoriteMovies.value.any { it.id == movie.id }
            movie.copy(isFavorite = isFavorite)
        }
    }

    fun getMovieDetails(movie: Movie) = MovieDetailsEntity(
        id = movie.id,
        originalLanguage = movie.originalLanguage,
        overview = movie.overview,
        posterPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        title = movie.title,
        voteCount = movie.voteCount,
        voteAverage = movie.voteAverage,
        isFavorite = movie.isFavorite
    )

    fun insertMovie(movie: MovieDetailsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val rowId = moviesUseCase.insertMovies(movie)

            _isInsertedSuccessfully.value = rowId != NEGATIVE_ONE_LONG
        }
    }

    fun removeMovie(movie: MovieDetailsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val rows = moviesUseCase.removeMovie(movie)

            _isDeletedSuccessfully.value = rows != ZERO
        }
    }

    fun removeIsInsertedSuccessfullyValue() {
        _isInsertedSuccessfully.value = null
    }

    fun removeIsDeletedSuccessfullyValue() {
        _isDeletedSuccessfully.value = null
    }
}