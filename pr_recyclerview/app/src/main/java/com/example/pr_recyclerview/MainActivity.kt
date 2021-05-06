package com.example.pr_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dogList = arrayListOf<Dog>(
        Dog("chochoc", "Male", "1"),
        Dog("breed pomerian", "Female", "3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = MainRvAdapter(this, dogList)

        //linearmanager의 경우 recyclerview의 각 item들을 배치고, item이 더이상 보이지 않을 때 재사용할것인지를 물어봄
        val lm = LinearLayoutManager(this)
        mRecyclerView.layoutManager = lm
        mRecyclerView.adapter = mAdapter

        mRecyclerView.setHasFixedSize(true)//크기가 변경될 수도 있고, 그러다보면 view크기가 변경됨
    }
}