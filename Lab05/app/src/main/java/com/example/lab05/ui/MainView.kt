package com.example.lab05.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.lab05.data.Movie
import com.example.lab05.data.MovieDatabase
import com.example.lab05.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedReader

class MainView(app: Application) {

    private var movieData = MutableLiveData<List<Movie>>()
    //databse
    private var movieDao = MovieDatabase.getDatabase(app).movieDao()


    init {
        CoroutineScope(Dispatchers.IO).launch {
                loadJsonMovie(app)

//            val data =movieDao.getAll()
//            movieData.postValue(data)
            //val movieList =

            //val movieList = movieDao.getAll()
            //movieData.postValue(movieList)//

        }
    }

    fun loadJsonMovie(app: Application){

        //load json
        val movieType = Types.newParameterizedType(List::class.java, Movie::class.java)

        val jsonText = app.assets.open("movie_details.json").use {
            it.bufferedReader().use { reader: BufferedReader ->
                reader.readText()
            }
        }
        //parsing the json using Moshi
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Movie>> = moshi.adapter(movieType)
        val movieList = adapter.fromJson(jsonText)
        //movieDao.insertMovies(movieList!!)

        movieDao.deleteAll()
        movieList?.forEach {
            movieDao.insertMovie(it)
        }
        // ovieData.postValue(movieList)
        //return movieList!!

    }

    fun getAllMovice(): LiveData<List<Movie>>{
//        val data = movieDao.getAll()
//        movieData.postValue(data)
        return movieDao.getAll()
        //return movieData
    }
//
    fun searchMovie(releaseDate: String): LiveData<List<Movie>>{

        return movieDao.searchReleaseDate(releaseDate)
    }
//    fun searchReleaseMove(): LiveData<List<Movie>>{
//
//        return n
//        //return movieDao.searchReleaseDate()
//    }
//
//    movieData.postValue(movieList)
//


}