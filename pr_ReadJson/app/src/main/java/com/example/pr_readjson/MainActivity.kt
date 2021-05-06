package com.example.pr_readjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pr_readjson.ui.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val FILENAME = "employee.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult.setText("")
        val mv = MainView(this, FILENAME)
    }
}