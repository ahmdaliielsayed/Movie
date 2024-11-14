package com.example.movielist.listofmovies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.listofmovies.data.dto.Movie
import com.example.movielist.listofmovies.domain.usecase.MoviesUseCase
import com.example.movielist.listofmovies.domain.utils.onError
import com.example.movielist.listofmovies.domain.utils.onSuccess
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
            }.onError {
                _isLoading.value = false
                _errorMessage.value = it.name
            }
        }
    }
}