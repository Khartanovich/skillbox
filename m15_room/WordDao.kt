package com.example.m15_room

import androidx.room.*
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query(value = "SELECT * FROM dictionary LIMIT 5")
    fun getFirstFiveWord(): Flow<List<Dictionary>>

    //    @Update()
    @Query("UPDATE dictionary SET number_repeat = number_repeat + 1  WHERE word = :newWord")
    suspend fun updateDictionary(newWord: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertWord(word: NewWordDictionary)
    suspend fun insertWord(word: Dictionary)

    //    @Delete
    @Query("DELETE FROM dictionary")
    suspend fun deleteAll()
}