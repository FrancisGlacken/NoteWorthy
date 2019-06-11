package com.deltorostudios.noteworthy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@PrimaryKey @ColumnInfo(name = "note") var note: String = "blank") {

}