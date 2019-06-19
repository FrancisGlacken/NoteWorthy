package com.deltorostudios.noteworthy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//TODO: Add an id
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate =  true) var id: Long?,
    @ColumnInfo(name = "note")
    var note: String = "blank") {

    constructor():this(null, "")

}