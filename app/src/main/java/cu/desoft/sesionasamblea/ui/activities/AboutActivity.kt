package cu.desoft.sesionasamblea.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cu.desoft.sesionasamblea.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAboutActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}