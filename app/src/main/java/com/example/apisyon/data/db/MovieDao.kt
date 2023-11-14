package com.example.apisyon.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apisyon.data.model.MovieDto


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE page=:page ")
    suspend fun getMovies(page: Int): MovieDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: MovieDto)

    @Query("DELETE FROM movies")
    suspend fun nukeTable()

}
