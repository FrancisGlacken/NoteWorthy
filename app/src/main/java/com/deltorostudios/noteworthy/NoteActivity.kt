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
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)


        // Get intent and set note text
        noteActivityEditText.setText(intent.getStringExtra("note_key"))

        // onClick for save button
        noteActivitySaveButton.setOnClickListener {
            val updatedNote = Note(intent.getLongExtra("id_key", 1),noteActivityEditText.text.toString())
            noteViewModel.updateNote(updatedNote)
            onBackPressed()
        }
    }
}
