package com.example.apisyon.data.model

data class MovieDto(
    val page: Int,
    val results: List<MovieResultDto>,
    val total_pages: Int,
    val total_results: Int
)