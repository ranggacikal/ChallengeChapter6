package com.ranggacikal.challengechapter5.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History ORDER BY id DESC")
    fun getAllHistory(): List<History>

    @Insert
    fun insert(vararg history: History)
}