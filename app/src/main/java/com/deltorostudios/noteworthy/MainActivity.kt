package com.deltorostudios.noteworthy

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager



class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    companion object {
        const val newNoteActivityRequestCode = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Use sharedPreferences to set the theme properly before view creation
        val prefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
        val editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit()
        val currentTheme = prefs.getInt("theme_key", 1)
        when (currentTheme) {
            1 -> {
                setTheme(R.style.RedTheme)
                editor.putInt("theme_key", 1)
                editor.apply()
            }
            2 -> {
                setTheme(R.style.GreenTheme)
                editor.putInt("theme_key", 2)
                editor.apply()
            }
            3 -> {
                setTheme(R.style.BlueTheme)
                editor.putInt("theme_key", 3)
                editor.apply()
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }

        val recView = findViewById<RecyclerView>(R.id.rec_view)
        val adapter = NoteListAdapter(this)
        val myLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //myLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        recView.adapter = adapter
        recView.layoutManager = myLayoutManager

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let {
                adapter.setNotes(it)
            }
        })
    }



    // Inflate menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    // Menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //TODO: Reuse the themes idea from punch clock, that shit worked good
        val editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit()

        when (item.itemId) {
            R.id.action_red -> {
                editor.putInt("theme_key", 1)
                editor.apply()
                finish()
                val intent = intent
                startActivity(intent)
                return true
            }
            R.id.action_green -> {
                editor.putInt("theme_key", 2)
                editor.apply()
                finish()
                val intent = intent
                startActivity(intent)
                return true
            }
            R.id.action_blue -> {
                editor.putInt("theme_key", 3)
                editor.apply()
                finish()
                val intent = intent
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Insert notes when code is requested?
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val newNote = Note()
                newNote.note = it.getStringExtra(NewNoteActivity.EXTRA_REPLY)
                noteViewModel.insertNote(newNote)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}
