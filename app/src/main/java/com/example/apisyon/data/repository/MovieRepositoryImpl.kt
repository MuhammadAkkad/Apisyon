package com.example.apisyon.data.repository

import com.example.apisyon.data.model.MovieDto
import com.example.apisyon.data.remote.ApiService
import com.example.apisyon.domain.MovieRepository
import com.example.apisyon.util.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository {

    override suspend fun getPopularMovies(page: Int): Response<MovieDto> {
        return apiService.getPopularMovies(API_KEY, page)
    }

}