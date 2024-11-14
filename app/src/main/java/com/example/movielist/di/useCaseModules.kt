package com.example.movielist.di

import com.example.movielist.domain.usecases.MoviesUseCase
import com.example.movielist.domain.usecases.MoviesUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModules = module {

    factoryOf(::MoviesUseCaseImpl) { bind<MoviesUseCase>() }
}