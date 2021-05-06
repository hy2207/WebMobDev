package com.example.lab05.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.sql.Date

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    val movie_id: String="",
    val plot_summary: String ="",
    val duration: String="",
    val genre: List<String>,
    val rating: String ="",
    val release_date: String="",
    val plot_synopsis: String=""
)

