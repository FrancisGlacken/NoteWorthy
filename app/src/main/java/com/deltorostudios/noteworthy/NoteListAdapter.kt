package com.deltorostudios.noteworthy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NoteListAdapter internal constructor(context: Context) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of words
    private val mRandom = Random()

    // onCreateView method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return NoteViewHolder(itemView)
    }

    // On Bind method
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        // noteItemView is the textView, the other two are for intents
        holder.noteItemView.text = current.note
        holder.currentNoteId = current.id
        holder.currentNoteContent = current.note
    }


    // Set notes
    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    // Item count
    override fun getItemCount() = notes.size


    // ViewHolder class
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView = itemView.findViewById(R.id.noteContentView)
        var currentNoteId: Long? = 0
        var currentNoteContent: String = ""

        init {
            // onClick for cards
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, NoteActivity::class.java)
                intent.putExtra("id_key", currentNoteId)
                intent.putExtra("note_key", currentNoteContent)
                itemView.context.startActivity(intent)
            }
        }
    }
}