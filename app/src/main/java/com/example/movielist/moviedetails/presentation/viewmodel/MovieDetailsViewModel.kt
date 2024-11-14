package com.example.movielist.moviedetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.domain.usecases.MoviesUseCase
import com.example.movielist.utils.Constants.NEGATIVE_ONE_LONG
import com.example.movielist.utils.Constants.ZERO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _isInsertedSuccessfully = MutableLiveData<Boolean?>(null)
    val isInsertedSuccessfully: LiveData<Boolean?> = _isInsertedSuccessfully

    private val _isDeletedSuccessfully = MutableLiveData<Boolean?>(null)
    val isDeletedSuccessfully: LiveData<Boolean?> = _isDeletedSuccessfully

    fun insertMovie(movie: MovieDetailsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val rowId = moviesUseCase.insertMovies(movie)

            _isInsertedSuccessfully.postValue(rowId != NEGATIVE_ONE_LONG)
        }
    }

    fun removeMovie(movie: MovieDetailsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val rows = moviesUseCase.removeMovie(movie)

            _isDeletedSuccessfully.postValue(rows != ZERO)
        }
    }
}