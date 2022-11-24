package cu.desoft.sesionasamblea.ui.activities

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.SesionAsambleaApp
import cu.desoft.sesionasamblea.adapters.DocumetAdapter
import cu.desoft.sesionasamblea.databinding.ActivityDocumentsBinding
import cu.desoft.sesionasamblea.ui.viewmodels.DocumentViewModel
import cu.desoft.sesionasamblea.utils.ClikPdf
import cu.desoft.sesionasamblea.utils.ItemClick
import java.io.File


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

        isReadStoragePermissionGranted()

        val documentoList = resources.getStringArray(R.array.Documents_name)


       documetAdapter = DocumetAdapter(documentoList.toList(),object : ItemClick{
            override fun clicked(pos: Int) {
                val intent = Intent(this@Documents,NoteDocument::class.java)
                intent.putExtra("id",pos + 1)
                startActivity(intent)
            }
        }, object : ClikPdf{
           override fun clicked(pos: Int) {
               openPDF(pos + 1)
           }
       })

        binding.rvDocuments.adapter = documetAdapter
        binding.rvDocuments.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.VERTICAL,false)

    }


    // Access pdf from storage and using to Intent get options to view application in available applications.
    private fun openPDF(doc : Int) {

        // Get the File location and file name.
        val file = File(Environment.getExternalStorageDirectory(), "Documentos Asamblea/${doc}.pdf")
        Log.d("pdfFIle", "" + file)

        // Get the URI Path of file.
        val uriPdfPath =
            FileProvider.getUriForFile(this, applicationContext.packageName + ".provider", file)
        Log.d("pdfPath", "" + uriPdfPath)

        // Start Intent to View PDF from the Installed Applications.
        val pdfOpenIntent = Intent(Intent.ACTION_VIEW)
        pdfOpenIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        pdfOpenIntent.clipData = ClipData.newRawUri("", uriPdfPath)
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf")
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        try {
            startActivity(pdfOpenIntent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            Toast.makeText(this, "There is no app to load corresponding PDF", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun isReadStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            }
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            }
            if (checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            }
            else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
            else{
               Toast.makeText(this,"No tiene permisos", Toast.LENGTH_LONG)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_addnote, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.nav_NoteAdd -> {
                startActivity(Intent(this, AddNoteActivity::class.java))
                true
            }
            else -> {
                false
            }
        }
    }

}
