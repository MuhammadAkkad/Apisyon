package com.example.apisyon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apisyon.data.model.MovieDto
import com.example.apisyon.data.model.MovieResultDto

@Database(
    entities = [MovieDto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}