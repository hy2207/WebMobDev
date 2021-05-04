package com.example.lab02

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.nio.charset.Charset
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.time.minutes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alphabet = ('A'..'Z')
        displayLetterList(alphabet)
        //refresh button click
        btnRefresh.setOnClickListener {
            displayLetterList(alphabet)
            txtResult.setText("")
            setClickEvent(true)
        }
        //load word list
        val gameDictionary = ArrayList<String>()
        val inputStream: InputStream = resources.openRawResource(R.raw.wordlist_300299846)
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.readLines().forEach{
            val items = it.split(",")
            for(item in items){
                gameDictionary.add(item)
            }
        }

        var player1Score = 0
        var player2Score = 0

        val player1 = intent.getStringExtra("player1")
        val player2 = intent.getStringExtra("player2")

        val setTime = intent.getIntExtra("time", 5)

        txtViewPlayer1.setText(player1)
        txtViewPlayer2.setText(player2)
        var currentPlayer = player1 //set default

        Toast.makeText(this, currentPlayer+ " turn!", Toast.LENGTH_LONG).show()

        var leftTime = (setTime)
        timer(period = 60 * 1000){
            leftTime--
            runOnUiThread {
                txtLeftTime.setText((leftTime+1).toString() + " min")
            }
            if (leftTime < 0){
                cancel()
                runOnUiThread {
                    endGame(player1Score, player2Score)
                    txtLeftTime.setText("Time Over")
                    txtLeftTime.setTextColor(Color.parseColor("#ffcc0000"))
                }

            }
        }

        //add button
        btnAdd.setOnClickListener {
            var userInput = txtResult.text.toString()
            var result: Boolean

            result = compareLetter(userInput, gameDictionary)

            if (currentPlayer == player1){
                if (result){
                    ++player1Score;
                    correctList.append(userInput)
                    correctList.append(" (" + currentPlayer + " +1)")
                    correctList.append("\n")
                } else {
                    --player1Score;
                    wrongList.append(userInput)
                    wrongList.append(" (" + currentPlayer + " -1)")
                    wrongList.append("\n")
                }
                txtViewScore1.setText(player1Score.toString())
                currentPlayer = player2
                Toast.makeText(this, currentPlayer+ " turn!", Toast.LENGTH_LONG).show()

            } else{
                if (result){
                    ++player2Score
                    correctList.append(userInput)
                    correctList.append(" (" + currentPlayer + " +1)")
                    correctList.append("\n")
                } else {
                    --player2Score
                    wrongList.append(userInput)
                    wrongList.append(" (" + currentPlayer + " -1)")
                    wrongList.append("\n")
                }
                txtViewScore2.setText(player2Score.toString())
                currentPlayer = player1
                Toast.makeText(this, currentPlayer+ " turn!", Toast.LENGTH_LONG).show()
            }
            setClickEvent(true)
            txtResult.setText("")
        }

        btnRestart.setOnClickListener {
            setClickEvent(true)
            currentPlayer = player1
            leftTime = setTime
            timer(period = 60 * 1000){
                leftTime--
                runOnUiThread {
                    txtLeftTime.setText((leftTime+1).toString() + " min")
                    txtLeftTime.setTextColor(Color.BLACK)
                }
                if (leftTime < 0){
                    cancel()
                    runOnUiThread {
                        endGame(player1Score, player2Score)
                        txtLeftTime.setText("Time Over")
                        txtLeftTime.setTextColor(Color.parseColor("#ffcc0000"))
                    }

                }
            }
            correctList.setText("")
            wrongList.setText("")
            player1Score = 0
            txtViewScore1.setText(player1Score.toString())
            txtViewPlayer1.setText(player1)
            player2Score = 0
            txtViewScore2.setText(player2Score.toString())
            txtViewPlayer2.setText(player2)
            txtResult.setText("")
        }

        btnEndGame.setOnClickListener {
            endGame(player1Score, player2Score)
        }
    }

    fun endGame(player1Score: Int, player2Score: Int){
        setClickEvent(false)
        txtResult.setText("")
        val player1 = txtViewPlayer1.text.toString()
        val player2 = txtViewPlayer2.text.toString()

        if(player1Score > player2Score){
            Toast.makeText(this,"CONGRATULATION WINNER IS " + player1 + " (Click restart if you want play one more)", Toast.LENGTH_LONG).show()
            txtViewPlayer1.setText(player1 + " (WINNER)")
        } else if(player1Score < player2Score){
            Toast.makeText(this,"CONGRATULATION WINNER IS " + player2 + " (Click restart if you want play one more)", Toast.LENGTH_LONG).show()
            txtViewPlayer2.setText(player2 + " (WINNER)")
        } else {
            Toast.makeText(this,"SAME SCORE! RESTART THE GAME :) ", Toast.LENGTH_LONG).show()
        }
    }


    fun displayLetterList( letter: CharRange){
        val randomAlphabet = List(16) { letter.random() }
        letter1.setText(randomAlphabet[0].toString())
        letter2.setText(randomAlphabet[1].toString())
        letter3.setText(randomAlphabet[2].toString())
        letter4.setText(randomAlphabet[3].toString())
        letter5.setText(randomAlphabet[4].toString())
        letter6.setText(randomAlphabet[5].toString())
        letter7.setText(randomAlphabet[6].toString())
        letter8.setText(randomAlphabet[7].toString())
        letter9.setText(randomAlphabet[8].toString())
        letter10.setText(randomAlphabet[9].toString())
        letter11.setText(randomAlphabet[10].toString())
        letter12.setText(randomAlphabet[11].toString())
        letter13.setText(randomAlphabet[12].toString())
        letter14.setText(randomAlphabet[13].toString())
        letter15.setText(randomAlphabet[14].toString())
        letter16.setText(randomAlphabet[15].toString())
    }

    fun compareLetter(userInput: String, gameDic: ArrayList<String>): Boolean{

        var indexDictionary: Int = 1

        for (dataList in gameDic){
            indexDictionary = userInput.toLowerCase().compareTo(dataList)
            if (indexDictionary == 0) {
                return true
                break;
            }
        }
        return false
    }

    fun selectLetter(view: View) {
        val selLetter = view as TextView
        when(selLetter.id){
            letter1.id->{
                txtResult.append(letter1.text.toString())
                setClickEvent(false)
                letter2.isClickable = true; letter5.isClickable = true; letter6.isClickable = true;
            }
            letter2.id->{
                txtResult.append(letter2.text.toString())
                setClickEvent(false)
                letter1.isClickable = true; letter5.isClickable = true; letter6.isClickable = true; letter7.isClickable = true; letter3.isClickable = true;
            }
            letter3.id->{
                txtResult.append(letter3.text.toString())
                setClickEvent(false)
                letter2.isClickable = true; letter6.isClickable = true; letter7.isClickable = true; letter8.isClickable = true; letter4.isClickable = true;
            }
            letter4.id->{
                txtResult.append(letter4.text.toString())
                setClickEvent(false)
                letter3.isClickable = true; letter7.isClickable = true; letter8.isClickable = true;
            }
            letter5.id->{
                txtResult.append(letter5.text.toString())
                setClickEvent(false)
                letter1.isClickable = true; letter2.isClickable = true; letter6.isClickable = true; letter10.isClickable = true; letter9.isClickable = true;
            }
            letter6.id->{
                txtResult.append(letter6.text.toString())
                setClickEvent(false)
                letter1.isClickable = true; letter2.isClickable = true; letter3.isClickable = true; letter7.isClickable = true; letter11.isClickable = true;
                letter10.isClickable = true; letter9.isClickable = true; letter5.isClickable = true;
            }
            letter7.id->{
                txtResult.append(letter7.text.toString())
                setClickEvent(false)
                letter2.isClickable = true; letter3.isClickable = true; letter4.isClickable = true; letter8.isClickable = true; letter12.isClickable = true;
                letter11.isClickable = true; letter10.isClickable = true; letter6.isClickable = true;
            }
            letter8.id->{
                txtResult.append(letter8.text.toString())
                setClickEvent(false)
                letter3.isClickable = true; letter4.isClickable = true; letter7.isClickable = true; letter11.isClickable = true; letter12.isClickable = true;
            }
            letter9.id->{
                txtResult.append(letter9.text.toString())
                setClickEvent(false)
                letter5.isClickable = true; letter6.isClickable = true; letter10.isClickable = true; letter14.isClickable = true; letter13.isClickable = true;
            }
            letter10.id->{
                txtResult.append(letter10.text.toString())
                setClickEvent(false)
                letter5.isClickable = true; letter6.isClickable = true; letter7.isClickable = true; letter11.isClickable = true; letter15.isClickable = true;
                letter14.isClickable = true; letter13.isClickable = true; letter9.isClickable = true;
            }
            letter11.id->{
                txtResult.append(letter11.text.toString())
                setClickEvent(false)
                letter6.isClickable = true; letter7.isClickable = true; letter8.isClickable = true; letter12.isClickable = true; letter16.isClickable = true;
                letter15.isClickable = true; letter14.isClickable = true; letter10.isClickable = true;
            }
            letter12.id->{
                txtResult.append(letter12.text.toString())
                setClickEvent(false)
                letter7.isClickable = true; letter8.isClickable = true; letter11.isClickable = true; letter15.isClickable = true; letter16.isClickable = true;
            }
            letter13.id->{
                txtResult.append(letter13.text.toString())
                setClickEvent(false)
                letter9.isClickable = true; letter10.isClickable = true; letter14.isClickable = true;
            }
            letter14.id->{
                txtResult.append(letter14.text.toString())
                setClickEvent(false)
                letter9.isClickable = true; letter10.isClickable = true; letter11.isClickable = true; letter15.isClickable = true; letter13.isClickable = true;
            }
            letter15.id->{
                txtResult.append(letter15.text.toString())
                setClickEvent(false)
                letter14.isClickable = true; letter10.isClickable = true; letter11.isClickable = true; letter12.isClickable = true; letter16.isClickable = true;
            }
            letter16.id->{
                txtResult.append(letter16.text.toString())
                setClickEvent(false)
                letter15.isClickable = true; letter11.isClickable = true; letter12.isClickable = true;
            }
        }
    }
    fun setClickEvent(click: Boolean){
        letter1.isClickable = click; letter2.isClickable = click; letter3.isClickable = click; letter4.isClickable = click; letter5.isClickable = click; letter6.isClickable = click;
        letter7.isClickable = click; letter8.isClickable = click; letter9.isClickable = click; letter10.isClickable = click; letter11.isClickable = click;
        letter12.isClickable = click; letter13.isClickable = click; letter14.isClickable = click; letter15.isClickable = click; letter16.isClickable = click;
    }

}
