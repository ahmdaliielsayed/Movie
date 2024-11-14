package com.example.movielist.koin

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.example.movielist.listofmovies.presentation.viewmodel.MoviesViewModel

val viewModelsModule = module {

    viewModelOf(::MoviesViewModel)
}