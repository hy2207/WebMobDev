package com.example.lab05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.lab05.data.Movie
import com.example.lab05.data.MovieDao
import com.example.lab05.ui.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    val viewModel: MainView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        viewModel.getAll().observe(this, Observer {
//            txtMovieList.text = it.toString()
//        })

        val movieResult = MainView(this.application).getAllMovice()
//        Log.e("Log", movieResult.toString())
        //displayResult(movieResult)

        btnSearch.setOnClickListener {
            val movieSearchList = MainView(this.application).searchMovie(txtSearchDate.text.toString())
            displayResult(movieSearchList)
        }
        //displayResult(movieSearchList)

    }
    fun displayResult(result: LiveData<List<Movie>>){
        result.observe(this, Observer {
            var s = ""
            for (m in it ?: emptyList()){
                s += "id: ${m.movie_id} realease date: ${m.release_date}\n"
            }
            txtMovieList.text = s
        })
    }
}