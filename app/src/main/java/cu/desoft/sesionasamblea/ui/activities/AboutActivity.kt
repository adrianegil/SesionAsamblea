package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.graphics.Paint
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

        binding.textViewDESOFTSiteWeb.setPaintFlags(binding.textViewDESOFTSiteWeb.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.textViewDESOFTEmail.setPaintFlags(binding.textViewDESOFTEmail.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        binding.btnDescription.setOnClickListener {
            startActivity(Intent(this, DescriptionActivity::class.java))
        }

    }
}