package cu.desoft.sesionasamblea.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.SesionAsambleaApp
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.databinding.ActivityDocumentsBinding
import cu.desoft.sesionasamblea.databinding.ActivityNoteDocumentBinding
import cu.desoft.sesionasamblea.ui.viewmodels.DocumentViewModel

class NoteDocument : AppCompatActivity() {

    private val documentViewModel: DocumentViewModel by viewModels {
        DocumentViewModel.DocumentViewModelFactory((this.application as SesionAsambleaApp).repository)
    }

    lateinit var binding: ActivityNoteDocumentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_document)

        binding = ActivityNoteDocumentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val document =  documentViewModel.getDocById(intent.getIntExtra("id", 0))
        binding.notedocumettext.setText(document.noteDoc)

        binding.noteSave.setOnClickListener {
            documentViewModel.insertDocument(Document(intent.getIntExtra("id", 0),binding.notedocumettext.text.toString()))
        }

    }
}