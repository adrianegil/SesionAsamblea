package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.data.entity.Note
import cu.desoft.sesionasamblea.ui.viewmodels.NoteViewModal
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdt: EditText
    lateinit var noteDescriptionEdt: EditText
    lateinit var addupdateBtn: Button

    lateinit var viewModal: NoteViewModal
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        // on below line we are initializing all our variables.
        noteTitleEdt = findViewById(R.id.titleinput)
        noteDescriptionEdt = findViewById(R.id.descriptioninput)
        addupdateBtn = findViewById(R.id.savebtn)


        // on below line we are initialing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // on below line we are getting data passed via an intent.
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")


            addupdateBtn.setText("Actualizar Nota")
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDescription)
        } else {
            addupdateBtn.setText("Guardar Nota")
        }

        // on below line we are adding
        // click listener to our save button.
        addupdateBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteDescriptionEdt.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    noteID = intent.getIntExtra("noteID", 0)
                    val updatedNote = Note(noteID, noteTitle, noteDescription, currentDateAndTime)

                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Nota Actualizada..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    noteID = intent.getIntExtra("noteID", 0)
                    viewModal.addNote(Note(noteID, noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Añadida", Toast.LENGTH_LONG).show()
                }
            }
            // opening the new activity on below line
            startActivity(Intent(applicationContext, NotepadActivity::class.java))
            this.finish()
        }
    }
}
