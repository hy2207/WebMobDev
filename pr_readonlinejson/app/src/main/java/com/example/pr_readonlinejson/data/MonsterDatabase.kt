package com.example.pr_readonlinejson.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Monster::class], version = 1, exportSchema = false)
abstract class MonsterDatabase: RoomDatabase() {
    abstract fun monserDa(): MonsterDao
    //object정의 할 때 사용하나봄
    companion object{
        @Volatile
        private var INSTANCE: MonsterDatabase ?= null

        fun getDatabase(context: Context): MonsterDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MonsterDatabase::class.java,
                        "monseters.db"
                    ).build()
                }
            }
            return INSTANCE!! //!!이거는 null 이 될 수 없다는 것을 단언하는 연산
        }
    }
}