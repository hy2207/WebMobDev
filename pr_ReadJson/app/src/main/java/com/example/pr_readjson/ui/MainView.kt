package com.example.pr_readjson.ui

import android.app.Activity
import android.util.Log
import com.example.pr_readjson.data.Employee
import com.example.pr_readjson.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_main.*

class MainView(ctx : Activity, fileName: String) {
    private val myType = Types.newParameterizedType(List::class.java, Employee::class.java)

    init {
        val text = FileHelper.getDataFromAssets(ctx, fileName)

        val moshi: Moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<Employee>> = moshi.adapter(myType)
        val empList = adapter.fromJson(text)

        for (e in empList ?: emptyList()){
            ctx.txtResult.append("${e.first_name} - ${e.last_name}\n")
            //Log.i(this.toString(), "${e.first_name} - ${e.last_name}")
        }

    }
}