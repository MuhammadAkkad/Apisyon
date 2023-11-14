package com.example.apisyon.data.remote

import com.example.apisyon.data.model.MovieDto
import com.example.apisyon.util.Constants.Companion.End_Point
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(End_Point)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): Response<MovieDto>
}