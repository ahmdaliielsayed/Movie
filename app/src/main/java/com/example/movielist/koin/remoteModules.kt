package com.example.movielist.koin

import com.example.movielist.listofmovies.data.network.APIService
import com.example.movielist.listofmovies.data.network.RemoteDataSource
import com.example.movielist.listofmovies.data.network.RemoteDataSourceImpl
import com.example.movielist.listofmovies.data.network.RetrofitInstance
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteModules = module {

    single<APIService> { RetrofitInstance.retrofitService }

    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
}