package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.databinding.ActivityMainBinding
import cu.desoft.sesionasamblea.ui.notepad.NotepadActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMainActivity)
        binding.notasFB.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this,
                    NotepadActivity::class.java
                )
            )
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.nav_About -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
        }
    }

}