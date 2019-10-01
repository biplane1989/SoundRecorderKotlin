package com.example.soundrecorderkotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soundrecorderkotlin.commol.OnListenerData

@Dao
interface RecordDAO {

    @Query("SELECT * FROM records")
    fun getAllRecords(): List<Record>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: Record)
}