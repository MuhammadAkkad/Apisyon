package com.example.apisyon.data.db

import androidx.room.TypeConverter
import com.example.apisyon.data.model.MovieResultDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun fromMovieResultsList(movieResults: List<MovieResultDto>?): String? {
        return Gson().toJson(movieResults)
    }

    @TypeConverter
    fun toMovieResultsList(movieResultsString: String?): List<MovieResultDto>? {
        return Gson().fromJson(
            movieResultsString,
            object : TypeToken<List<MovieResultDto>>() {}.type
        )
    }
}