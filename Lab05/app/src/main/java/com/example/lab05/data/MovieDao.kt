package com.example.lab05.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * from movies")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * from movies WHERE release_date LIKE :search ")
    fun  searchReleaseDate(search: String): LiveData<List<Movie>>

    @Query("DELETE from movies")
    fun deleteAll()

}