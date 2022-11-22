package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.SesionAsambleaApp
import cu.desoft.sesionasamblea.adapters.DocumetAdapter
import cu.desoft.sesionasamblea.data.entity.Deputy
import cu.desoft.sesionasamblea.databinding.ActivityMainBinding
import cu.desoft.sesionasamblea.ui.activitie.AssistanceActivity
import cu.desoft.sesionasamblea.ui.viewmodels.DeputyViewModel
import cu.desoft.sesionasamblea.utils.ItemClick
import cu.desoft.sesionasamblea.utils.UserHelper
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val deputyViewModel: DeputyViewModel by viewModels {
        DeputyViewModel.DeputyViewModelFactory((this.application as SesionAsambleaApp).deputyRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMainActivity)

        binding.button.setOnClickListener {
            startActivity(Intent(this, Documents::class.java))
        }
        deputyViewModel.getDeputyByRegister(UserHelper.getDeputyRegister(this)).observe(this) {
            binding.txtViewDeputyName.text = it.name
            binding.txtViewDeputyOrganization.text = it.organization
            binding.txtViewDeputyAddress.text = it.province
            binding.txtViewDeputyRegisterNumber.text = it.register.toString()
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
                deputyViewModel.deleteAllDeputies()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
        }
    }

}