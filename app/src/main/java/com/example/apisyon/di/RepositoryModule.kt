package com.example.apisyon.di

import com.example.apisyon.data.repository.MovieRepositoryImpl
import com.example.apisyon.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        weatherRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

}