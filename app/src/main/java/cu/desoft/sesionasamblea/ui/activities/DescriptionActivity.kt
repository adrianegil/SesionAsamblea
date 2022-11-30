package cu.desoft.sesionasamblea.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cu.desoft.sesionasamblea.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDescriptionActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}