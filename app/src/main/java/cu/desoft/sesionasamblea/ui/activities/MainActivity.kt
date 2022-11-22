package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.databinding.ActivityMainBinding
import cu.desoft.sesionasamblea.ui.activities.AssistanceActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMainActivity)

        val pref = applicationContext.getSharedPreferences(
            "MyPref",
            MODE_PRIVATE
        )
       binding.txtViewDeputyName.setText(pref.getString("nombre", null).toString())
        binding.txtViewDeputyRegisterNumber.setText(pref.getString("registro", null).toString())
        binding.txtViewDeputyAddress.setText(pref.getString("provincia", null).toString())
        binding.txtViewDeputyOrganization.setText(pref.getString("organismo", null).toString())





        binding.button.setOnClickListener {
            println("pinga")
            startActivity(Intent(this,Documents::class.java))
//            startActivity(Intent(this, Documents::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.nav_Assistance -> {
                startActivity(Intent(this, AssistanceActivity::class.java))
                true
            }
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