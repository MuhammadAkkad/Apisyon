package com.example.apisyon.ui.movieList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.apisyon.data.paging.MoviePagingSource
import com.example.apisyon.data.repository.MovieRepositoryImpl
import com.example.apisyon.ui.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl,
    private val application: Application
) :
    ViewModel() {

    val movies: LiveData<PagingData<MovieModel>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { MoviePagingSource(repository, application) }
    ).liveData.cachedIn(viewModelScope)

}