package com.example.apisyon.data.paging

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apisyon.data.model.Mappers.toMovieModels
import com.example.apisyon.data.model.MovieDto
import com.example.apisyon.domain.MovieRepository
import com.example.apisyon.ui.model.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviePagingSource(
    private val repository: MovieRepository,
    private val application: Application
) :
    PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            if (!isNetworkAvailable()) {
                getDataFromDb(position)
            } else {
                getDataFromApi(position)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    private suspend fun getDataFromApi(position: Int): LoadResult<Int, MovieModel> {
        val movieList = repository.getPopularMovies(position)

        return if (movieList?.isSuccessful == true && movieList.body() != null) {
            val movies = movieList.body()
            saveDataToDB(movies)

            LoadResult.Page(
                data = movieList.body()!!.toMovieModels(),
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (position == movieList.body()!!.results.size) null else (position + 1)
            )

        } else {
            LoadResult.Error(throw Exception("No Response"))
        }
    }

    private suspend fun getDataFromDb(position: Int): LoadResult<Int, MovieModel> {
        val movieList = repository.getLocalMovies(position)

        return if (movieList != null && !movieList.results.isNullOrEmpty()) {
            LoadResult.Page(
                data = movieList.toMovieModels(),
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (position > movieList.page) null else (position + 1)
            )
        } else {
            LoadResult.Error(throw Exception("No Response"))
        }
    }

    private fun saveDataToDB(movies: MovieDto?) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveDataToDb(movies)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}

