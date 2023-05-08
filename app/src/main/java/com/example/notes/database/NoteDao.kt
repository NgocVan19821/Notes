package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
    @Delete
    fun delete(note: Note)
    @Query("Select * From notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
    @Query("UPDATE notes_table Set title = :title, note = :note WHERE id = :id")
    fun update(id: Int?, title: String?, note: String?)
}