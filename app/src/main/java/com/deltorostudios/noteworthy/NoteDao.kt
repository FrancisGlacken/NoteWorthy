package com.deltorostudios.noteworthy

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * from note_table ORDER BY note ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    fun updateNote(vararg note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()
}