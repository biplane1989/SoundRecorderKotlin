package com.example.soundrecorderkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Record::class), version = 1)
abstract class DataSingleton : RoomDatabase() {

    abstract fun recordDAO(): RecordDAO

    companion object {
        private var INSTANCE: DataSingleton? = null
        fun getInstance(context: Context): DataSingleton {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DataSingleton::class.java,
                    "RecordDB"
                ).build()
            }
            return INSTANCE as DataSingleton
        }
    }
}