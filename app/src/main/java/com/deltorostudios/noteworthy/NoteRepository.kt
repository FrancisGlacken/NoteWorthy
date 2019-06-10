package com.deltorostudios.noteworthy

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }


}