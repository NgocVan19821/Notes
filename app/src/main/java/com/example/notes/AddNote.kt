package com.example.notes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes.models.Note
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {

    private lateinit var note: Note
    private lateinit var old_note: Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            etTitle.setText(old_note.title)
            etNote.setText(old_note.note)
            isUpdate = true
        }catch (e: Exception){
            e.printStackTrace()
        }

        ic_done.setOnClickListener {
            val titleN = etTitle.text.toString()
            val noteN = etTitle.text.toString()

            if (titleN.isNotEmpty() || noteN.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
                note = if (isUpdate){
                    Note(old_note.id, titleN, noteN, formatter.format(Date()))
                }else{
                    Note(null, titleN, noteN, formatter.format(Date()))

                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this, "Please enter some datas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


        }
    }
}