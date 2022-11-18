package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.JsonObject
import cu.desoft.sesionasamblea.api.Login_API
import cu.desoft.sesionasamblea.api.RetrofitClient
import cu.desoft.sesionasamblea.data.model.Login_Model
import cu.desoft.sesionasamblea.databinding.ActivityLoginBinding
import cu.desoft.sesionasamblea.managers.AssistanceListManager
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

            doLogin()
        }
    }

    fun doLogin() {
        if (binding.editTextNoFolio.text.toString()
                .equals("", ignoreCase = true) && binding.editTextNoRegister.text.toString()
                .equals("", ignoreCase = true)
        ) {
            Toast.makeText(
                applicationContext,
                "Folio y Registro Requeridos",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        retrieveDataOut()
    }


    private fun retrieveDataOut() {

        try {
            binding.swipeRefreshLogin.isRefreshing = true
            val assistanceManager = AssistanceListManager()
            val call: Call<JsonObject> = assistanceManager.login(
                Login(
                    binding.editTextNoRegister.text.toString(),
                    binding.editTextNoFolio.text.toString()

                )
            )
            call.enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                    if (null != response.body() && response.code() == 200) {
                        val element = response.body()!!.asJsonObject
                        val diputado = element.asJsonObject["diputado"].asJsonObject

                        val pref = applicationContext.getSharedPreferences(
                            "MyPref",
                            MODE_PRIVATE
                        )
                        val editor: SharedPreferences.Editor = pref.edit()

                        editor.putString("token", element.asJsonObject["token"].asString)
                        editor.putString("nombre", diputado.asJsonObject["nombre"].asString)
                        editor.putString("registro", diputado.asJsonObject["registro"].asString)
                        editor.putString("provincia", diputado.asJsonObject["provincia"].asString)
                        editor.putString("organismo", diputado.asJsonObject["organismo"].asString)
                        editor.commit()


                        binding.swipeRefreshLogin.isRefreshing = false
                        val intent = Intent().setClass(
                            applicationContext,
                            MainActivity::class.java
                        )
                        startActivity(intent)
                        finish()


                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Credenciales Inválidas",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    binding.swipeRefreshLogin.isRefreshing = false
                }

                override fun onFailure(call: Call<JsonObject?>, throwable: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Problemas en la conexión",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.swipeRefreshLogin.isRefreshing = false

                }
            })
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Problemas en la conexión",
                Toast.LENGTH_SHORT
            ).show()
            binding.swipeRefreshLogin.isRefreshing = false


        }

        LogInSAMPP()
    }


    fun LogInSAMPP() {


        var Login_API = RetrofitClient.getRetrofit().create(Login_API::class.java)

        val call: Call<Login_Model> = Login_API.LogIn(Login(78042125416, "543423.A"))

        call.enqueue(object : Callback<Login_Model?> {
            override fun onResponse(call: Call<Login_Model?>, response: Response<Login_Model?>) {

                var loginModel = response.body()

                //goToMainActivity()

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

}





