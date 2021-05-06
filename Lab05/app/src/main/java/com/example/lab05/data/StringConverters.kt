package com.example.lab05.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.sql.Date

class StringConverters {


    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

}