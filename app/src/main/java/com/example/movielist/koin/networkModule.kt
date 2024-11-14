package com.example.movielist.koin

import com.example.movielist.network.APIService
import com.example.movielist.listofmovies.data.remote.RemoteDataSource
import com.example.movielist.listofmovies.data.remote.RemoteDataSourceImpl
import com.example.movielist.network.RetrofitInstance
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single<APIService> { RetrofitInstance.retrofitService }

    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
}