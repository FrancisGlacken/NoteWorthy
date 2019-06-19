package com.deltorostudios.noteworthy

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    @WorkerThread
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }


}