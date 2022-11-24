package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

     var document: Document = Document(0,null,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_document)

        binding = ActivityNoteDocumentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("id", intent.getIntExtra("id",0).toString())

        documentViewModel.allDocument.observe(this){
            it.map { doc->
                if (doc.documentID == intent.getIntExtra("id",0)){
                    document = doc
                    binding.notedocumettext.setText(doc.noteDoc)

                }
            }
        }

//        binding.notedocumettext.setText(document.noteDoc)

        binding.noteSave.setOnClickListener {
            if(document.noteDoc == null){
                documentViewModel.addddddNote(Document(intent.getIntExtra("id", 0),null,binding.notedocumettext.text.toString()))
            }

            if(document.noteDoc !=null){
                Log.d("idsssss", intent.getIntExtra("id", 0).toString())
                documentViewModel.addNote(
                    Document(
                        intent.getIntExtra("id", 0),
                        null,
                        binding.notedocumettext.text.toString()
                    )
                )
            }

        val intent = Intent(this@NoteDocument,Documents::class.java)
            startActivity(intent)
        }

    }
}