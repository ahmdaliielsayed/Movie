package com.example.movielist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movielist.domain.dto.MovieDetailsEntity
import com.example.movielist.utils.Constants.ONE

@Database(entities = [MovieDetailsEntity::class], version = ONE)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "movie_database"
                )
                    .build()
                    .also { createdInstance ->
                        instance = createdInstance
                    }
            }
        }
    }
}