package com.example.movielist.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.example.movielist.listofmovies.presentation.viewmodel.MoviesViewModel
import com.example.movielist.moviedetails.presentation.viewmodel.MovieDetailsViewModel

val viewModelsModule = module {

    viewModelOf(::MoviesViewModel)
    viewModelOf(::MovieDetailsViewModel)
}