package com.example.soundrecorderkotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Records")
data class Record(

    @PrimaryKey
    var id: Long?,

    @ColumnInfo(name = "recording_name")
    var recordingName: String,

    @ColumnInfo(name = "file_path")
    var filePath: String,

    @ColumnInfo(name = "length")
    var length: Long,

    @ColumnInfo(name = "time_added")
    var timeAdded: Long
)