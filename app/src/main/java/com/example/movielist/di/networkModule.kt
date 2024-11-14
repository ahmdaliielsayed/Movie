package com.example.movielist.di

import com.example.movielist.data.remote.APIService
import com.example.movielist.listofmovies.data.remote.RemoteDataSource
import com.example.movielist.listofmovies.data.remote.RemoteDataSourceImpl
import com.example.movielist.data.remote.RetrofitService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single<APIService> { RetrofitService.retrofitService }

    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
}