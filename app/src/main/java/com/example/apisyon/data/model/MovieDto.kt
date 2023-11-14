package com.example.apisyon.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDto(
    @PrimaryKey
    val page: Int,
    val results: List<MovieResultDto>,
    val total_pages: Int,
    val total_results: Int
)