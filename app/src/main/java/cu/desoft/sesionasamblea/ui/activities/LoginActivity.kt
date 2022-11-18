package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonArray
import cu.desoft.sesionasamblea.api.Login_API
import cu.desoft.sesionasamblea.api.RetrofitClient
import cu.desoft.sesionasamblea.data.model.Login_Model
import cu.desoft.sesionasamblea.databinding.ActivityLoginBinding
import cu.desoft.sesionasamblea.logic.Assistance
import cu.desoft.sesionasamblea.managers.AssistanceListManager
import cu.desoft.sesionasamblea.ui.notepad.AssistanceAdapter
import cu.desoft.sesionasamblea.utils.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogIn.setOnClickListener {

            LogInSAMPP()
        }
    }


    fun LogInSAMPP() {


        var Login_API = RetrofitClient.getRetrofit().create(Login_API::class.java)

        val call: Call<Login_Model> = Login_API.LogIn(Login(78042125416, "543423.A"))

        call.enqueue(object : Callback<Login_Model?> {
            override fun onResponse(call: Call<Login_Model?>, response: Response<Login_Model?>) {

                var loginModel = response.body()

                goToMainActivity()

            }

            override fun onFailure(call: Call<Login_Model?>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Problemas en la conexión" + t.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}




