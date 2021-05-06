package com.example.pr_readonlinejson.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monsters")
data class Monster (
    @PrimaryKey(autoGenerate = true)
    val monster: Int,
    val monsterName: String,
    val imagFile: String,
    val caption: String,
    val descriiption: String,
    val price: Double,
    val scariness: Int
    )