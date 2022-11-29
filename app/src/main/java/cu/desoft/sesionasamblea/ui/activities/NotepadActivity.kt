package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.button.MaterialButton
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.adapters.NoteClickDeleteInterface
import cu.desoft.sesionasamblea.adapters.NoteClickInterface
import cu.desoft.sesionasamblea.adapters.NoteRVAdapter
import cu.desoft.sesionasamblea.data.entity.Note
import cu.desoft.sesionasamblea.databinding.ActivityAboutBinding
import cu.desoft.sesionasamblea.databinding.ActivityNotepadBinding
import cu.desoft.sesionasamblea.ui.viewmodels.NoteViewModal

class NotepadActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface{

    lateinit var notesRV: RecyclerView
    lateinit var addBT: MaterialButton
    lateinit var viewModal: NoteViewModal

    lateinit var binding: ActivityNotepadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notepad)

        binding = ActivityNotepadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAboutActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        notesRV = binding.recyclerview
        addBT = binding.addnewnotebtn
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this, this, this)
        notesRV.adapter = noteRVAdapter
        viewModal = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)
        viewModal.allNotes.observe(this, Observer { list-> list?.let{noteRVAdapter.updateList(it)}})

        addBT.setOnClickListener {
            val intent = Intent(this@NotepadActivity, AddNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@NotepadActivity, AddNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)

        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {


        MaterialDialog(this).show {
            this.title(text = "Eliminar")
            this.message(text = "¿Desea eliminar la nota?")

            positiveButton(text = "Aceptar")

            negativeButton(text =  "Cancelar")

            positiveButton {
                        viewModal.deleteNote(note)
                Toast.makeText(this@NotepadActivity,"${note.noteTitle} Eliminada", Toast.LENGTH_LONG).show()
            }
            negativeButton {
                this.dismiss()
            }
        }
    }
}