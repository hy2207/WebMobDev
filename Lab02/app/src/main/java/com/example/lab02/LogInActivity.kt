package com.example.lab02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("player1", editTxtPlayer1.text.toString())
            intent.putExtra("player2", editTxtPlayer2.text.toString())
            intent.putExtra("time", setTime.text.toString().toInt())
            startActivity(intent)
        }
    }
}