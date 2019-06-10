package com.deltorostudios.noteworthy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Note::class), version = 1)
public abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build() // .addCallback(NoteDatabaseCallback(scope))
                INSTANCE = instance
                return instance
            }
        }

//        fun populateDatabase(noteDao: NoteDao) {
//            noteDao.deleteAll()
//
//            var note = Note(1, "Hello")
//            noteDao.insert(note)
//            note = Note(2, "World!")
//            noteDao.insert(note)
//        }
    }




//    private class NoteDatabaseCallback(
//        private val scope: CoroutineScope) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch(Dispatchers.IO) {
//                    populateDatabase(database.noteDao())
//                }
//            }
//        }
//    }
}