package com.deltorostudios.noteworthy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class NoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        val noteActivityEditText: EditText = findViewById(R.id.note_activity_edit_text)
        val noteActivitySaveButton: Button = findViewById(R.id.note_save_button)
        val noteActivityDeleteButton: Button = findViewById(R.id.note_delete_button)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        // Get intent and set note text
        noteActivityEditText.setText(intent.getStringExtra("note_key"))

        // onClick for save button
        noteActivitySaveButton.setOnClickListener {
            // Create a note with desired content and update it by the id
            val updatedNote = Note(intent.getLongExtra("id_key", 1),noteActivityEditText.text.toString())
            noteViewModel.updateNote(updatedNote)
            onBackPressed()
        }

        // onClick for delete button
        //Todo: Make this work properly, it's not deleting the note
        noteActivityDeleteButton.setOnClickListener {
            // Create a note with the values identical to the one we want deleted and send it through the viewmodel
            val noteToDelete = Note(intent.getLongExtra("id_key", 1),noteActivityEditText.text.toString())
            noteViewModel.deleteNote(noteToDelete)
            onBackPressed()
        }
    }
}
