package com.example.movielist.di

import com.example.movielist.listofmovies.data.repository.MoviesRepositoryImpl
import com.example.movielist.listofmovies.domain.repository.MoviesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoriesModule = module {

    singleOf(::MoviesRepositoryImpl) { bind<MoviesRepository>() }
}