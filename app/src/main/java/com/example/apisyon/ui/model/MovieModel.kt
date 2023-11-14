package com.example.apisyon.ui.model

import java.io.Serializable

data class MovieModel(
    val id: Int?,
    val title: String?,
    val year: String?,
    val rank: String?,
    val color: MovieGrade?,
    val posterUrl: String?
) : Serializable
