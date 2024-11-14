package com.example.movielist.koin

import com.example.movielist.listofmovies.domain.usecase.MoviesUseCase
import com.example.movielist.listofmovies.domain.usecase.MoviesUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModules = module {

    factoryOf(::MoviesUseCaseImpl) { bind<MoviesUseCase>() }
}