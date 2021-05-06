package com.example.lab05.data

import android.content.Context
import androidx.room.*

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(StringConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
