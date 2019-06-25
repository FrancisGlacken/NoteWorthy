package com.deltorostudios.noteworthy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewNoteActivity : AppCompatActivity() {

    private lateinit var editNoteView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        // Use sharedPreferences to set the theme properly before view creation
        val prefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
        val currentTheme = prefs.getInt("theme_key", 1)
        when (currentTheme) {
            1 -> setTheme(R.style.RedTheme)
            2 -> setTheme(R.style.GreenTheme)
            3 -> setTheme(R.style.BlueTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        editNoteView = findViewById(R.id.edit_note)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNoteView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val noteString = editNoteView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, noteString)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.deltorostudios.noteworthy.REPLY"
    }
}