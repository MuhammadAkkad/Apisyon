package com.example.apisyon.domain

import com.example.apisyon.data.model.MovieDto
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Response<MovieDto>?
    suspend fun getOfflinePopularMovies(page: Int): MovieDto?
    suspend fun saveMoviesToDb(movies: MovieDto?)
}