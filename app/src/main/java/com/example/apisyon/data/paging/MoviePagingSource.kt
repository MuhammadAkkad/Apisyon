package com.example.apisyon.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apisyon.ui.model.MovieModel
import com.example.apisyon.data.model.Mappers.toMovieModels
import com.example.apisyon.domain.MovieRepository

class MoviePagingSource(
    private val repository: MovieRepository
) :
    PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            val movieList = repository.getPopularMovies(position)

            if (movieList.isSuccessful && movieList.body() != null) {
                LoadResult.Page(
                    data = movieList.body()!!.toMovieModels(),
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == movieList.body()!!.results.size) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
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
}
