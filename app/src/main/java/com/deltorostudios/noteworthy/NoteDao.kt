package com.deltorostudios.noteworthy

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * from note_table ORDER BY note ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    fun updateNote(vararg note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()
}