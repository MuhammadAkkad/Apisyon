package com.example.apisyon.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.apisyon.ui.model.MovieModel
import com.example.apisyon.data.paging.MoviePagingSource
import com.example.apisyon.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl) :
    ViewModel() {

    val movies: LiveData<PagingData<MovieModel>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MoviePagingSource(repository) }
    ).liveData.cachedIn(viewModelScope)

}