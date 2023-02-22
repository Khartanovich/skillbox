package com.example.m15_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class NewWordDictionary(
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String? = null,
    @ColumnInfo(name = "number_repeat")
    val numberRepeat: Int
)
