package com.example.movielist.di

import com.example.movielist.data.local.AppDatabase
import com.example.movielist.data.local.MovieDao
import com.example.movielist.listofmovies.data.local.LocalDataSource
import com.example.movielist.listofmovies.data.local.LocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val localModules = module {

    single<MovieDao> { AppDatabase.getInstance(androidContext().applicationContext).movieDao() }

    singleOf(::LocalDataSourceImpl) { bind<LocalDataSource>() }
}