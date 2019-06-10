package com.deltorostudios.noteworthy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int,
    @ColumnInfo(name = "note")
    val note: String)