package com.example.apisyon.data.model

import com.example.apisyon.ui.model.MovieModel
import com.example.apisyon.ui.model.MovieGrade
import com.example.apisyon.util.Constants.Companion.Img_URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Mappers {

    private fun MovieResultDto.toMovieModel(): MovieModel {
        val title = title
        val year = release_date.extractYear()
        val rank = vote_average.formatDoubleToString()
        val posterUrl = poster_path.toUrl()
        val rankColor = when {
            vote_average < 7.0 -> MovieGrade.LOW
            vote_average in 7.0..9.0 -> MovieGrade.MEDIOCRE
            else -> MovieGrade.HIGH
        }

        return MovieModel(id, title, year, rank, rankColor, posterUrl)
    }

    fun MovieDto.toMovieModels(): List<MovieModel> {
        return results.map { it.toMovieModel() }
    }

    private fun String.extractYear(): String {
        return try {
            val date = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
            date.year.toString()
        } catch (e: Exception) {
            ""
        }
    }

    private fun Double.formatDoubleToString(): String {
        return "%.1f/10".format(this)
    }

    private fun String.toUrl(): String {
        return Img_URL + this
    }
}


