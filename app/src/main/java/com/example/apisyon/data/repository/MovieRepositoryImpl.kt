package com.example.apisyon.data.repository

import com.example.apisyon.data.db.AppDatabase
import com.example.apisyon.data.model.MovieDto
import com.example.apisyon.data.remote.ApiService
import com.example.apisyon.domain.MovieRepository
import com.example.apisyon.util.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) :
    MovieRepository {

    override suspend fun getPopularMovies(page: Int): Response<MovieDto>? {
        if (page > 5) return null /*Liste 100 item içermeli*/
        return apiService.getPopularMovies(API_KEY, page)
    }

    override suspend fun getOfflinePopularMovies(page: Int): MovieDto? {
        return appDatabase.movieDao().getMovies(page)
    }

    override suspend fun saveMoviesToDb(movies: MovieDto?) {
        movies?.let { appDatabase.movieDao().insertMovies(it) }
    }
}