package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.SesionAsambleaApp
import cu.desoft.sesionasamblea.adapters.DocumetAdapter
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.databinding.ActivityDocumentsBinding
import cu.desoft.sesionasamblea.ui.viewmodels.DocumentViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cu.desoft.sesionasamblea.utils.ItemClick


class Documents : AppCompatActivity() {

    private val documentViewModel: DocumentViewModel by viewModels {
        DocumentViewModel.DocumentViewModelFactory((this.application as SesionAsambleaApp).repository)
    }

    lateinit var documetAdapter: DocumetAdapter
    lateinit var binding: ActivityDocumentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        binding = ActivityDocumentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDocuments)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        poblateList()

        documentViewModel.allDocument.observe(this) {
            documetAdapter = DocumetAdapter(it, object : ItemClick {
                override fun clicked(pos: Int) {
                   println(it[pos].documentID)
                }
            })
            binding.rvListDocument.adapter = documetAdapter
            binding.rvListDocument.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun poblateList(){

        documetListExample.map {
            documentViewModel.insertDocument(it)
        }
    }

    private val documetListExample = listOf(
        Document(0,"Test Document1"),
        Document(0,"Test Document2"),
        Document(0,"Test Document3"),
    )

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_addnote, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.nav_NoteAdd -> {
                startActivity(Intent(this, NotepadActivity::class.java))
                true
            }

            else -> {
                true
            }
        }
    }
}