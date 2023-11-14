package com.example.apisyon.ui.model

data class MovieModel(
    val id: Int,
    val title: String,
    val year: String,
    val rank: String,
    val color: MovieGrade,
    val posterUrl: String
)
