package com.example.apisyon.di

import android.content.Context
import androidx.room.Room
import com.example.apisyon.data.db.AppDatabase
import com.example.apisyon.data.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "DATABASE"
        ).build()
    }

    @Provides
    fun provideShipDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }


}