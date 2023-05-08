package com.example.notes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notes.R
import com.example.notes.models.Note
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.random.Random

class NotesAdapter(
    private val context: Context,
    val listener: NotesClickListener

): RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
    private val NotesList =  ArrayList<Note>()
    private val fullList =  ArrayList<Note>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return NotesAdapter.MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.itemView.title.text = currentNote.title
        holder.itemView.title.isSelected = true
        holder.itemView.note.text = currentNote.note
        holder.itemView.date.text = currentNote.date
        holder.itemView.date.isSelected =true
        holder.itemView.notes_layout.setBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))
        holder.itemView.notes_layout.setOnClickListener {
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }
        holder.itemView.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(NotesList[holder.adapterPosition], holder.itemView.notes_layout)
            true
        }

    }


    override fun getItemCount(): Int {
        return NotesList.size
    }
    fun updateList(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }
    fun filterList(search: String){
        NotesList.clear()
        for (item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                        item.note?.lowercase()?.contains(search.lowercase()) == true){
                            NotesList.add(item)
                }
        }
        notifyDataSetChanged()
    }
    fun randomColor(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.note1)
        list.add(R.color.note2)
        list.add(R.color.note3)
        list.add(R.color.note4)
        list.add(R.color.note5)
        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]


    }
    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note:Note, view: LinearLayout )
    }

}