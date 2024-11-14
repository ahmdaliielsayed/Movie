package com.example.movielist

import android.app.Application
import com.example.movielist.di.localModules
import com.example.movielist.di.remoteModule
import com.example.movielist.di.repositoriesModule
import com.example.movielist.di.useCaseModules
import com.example.movielist.di.viewModelsModule
import com.example.movielist.utils.Constants.API_KEY
import com.example.movielist.utils.Constants.BASE_URL
import com.example.movielist.utils.Constants.IMAGE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    companion object {
        // Used to load the 'movielist' library on application startup.
        init {
            System.loadLibrary("movielist")
        }
    }

    override fun onCreate() {
        super.onCreate()

        BASE_URL = getBaseUrl()
        IMAGE_URL = getImageUrl()
        API_KEY = getApiKey()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    remoteModule,
                    localModules,
                    repositoriesModule,
                    useCaseModules,
                    viewModelsModule,
                )
            )
        }
    }

    /**
     * A native method that is implemented by the 'movielist' native library,
     * which is packaged with this application.
     */
    private external fun getBaseUrl(): String
    private external fun getImageUrl(): String
    private external fun getApiKey(): String
}