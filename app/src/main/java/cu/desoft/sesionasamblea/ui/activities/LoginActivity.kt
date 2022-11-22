package cu.desoft.sesionasamblea.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.SesionAsambleaApp
import cu.desoft.sesionasamblea.api.Login_API
import cu.desoft.sesionasamblea.api.RetrofitClient
import cu.desoft.sesionasamblea.data.model.Login_Model
import cu.desoft.sesionasamblea.databinding.ActivityLoginBinding
import cu.desoft.sesionasamblea.ui.viewmodels.DeputyViewModel
import cu.desoft.sesionasamblea.utils.Login
import cu.desoft.sesionasamblea.utils.UserHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val deputyViewModel: DeputyViewModel by viewModels {
        DeputyViewModel.DeputyViewModelFactory((this.application as SesionAsambleaApp).deputyRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogIn.setOnClickListener {
            binding.progressBarLogin.visibility = View.VISIBLE
            validateFields()
        }
    }

    fun validateFields() {
        if (binding.editTextNoRegister.text.toString()
                .isEmpty() || binding.editTextNoFolio?.text.toString().isEmpty()
        ) {
            Toast.makeText(
                applicationContext,
                getString(R.string.must_fill_fields),
                Toast.LENGTH_SHORT
            ).show()
            binding.progressBarLogin.visibility = View.INVISIBLE
        } else {
            LogInSAMPP()
        }
    }

    fun LogInSAMPP() {

        var Login_API = RetrofitClient.getRetrofit().create(Login_API::class.java)

        val call: Call<Login_Model> = Login_API.LogIn(Login(78042125416, "543423.A"))

        call.enqueue(object : Callback<Login_Model?> {
            override fun onResponse(call: Call<Login_Model?>, response: Response<Login_Model?>) {
                Log.e("Body", response.code().toString())
                if (response.isSuccessful) {
                    var loginModel = response.body() as Login_Model
                    // Log.e("Body", loginModel.token)
                    UserHelper.saveToken(loginModel.token, this@LoginActivity)
                    /*
                      Log.e("Body", loginModel.deputy?.name.toString())
                      Log.e("Body", loginModel.deputy?.organization.toString())
                      Log.e("Body", loginModel.deputy?.province.toString())
                      Log.e("Body", loginModel.deputy?.register.toString())
                     */
                    deputyViewModel.insertDeputy(loginModel.deputy!!)
                    UserHelper.saveDeputyRegister(loginModel.deputy!!.register, this@LoginActivity)
                    goToMainActivity()
                } else if (response.code() == 401 || response.code() == 500) {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.failed_authentication),
                        Toast.LENGTH_SHORT
                    )
                    binding.editTextNoRegister.setText("")
                    binding.editTextNoFolio?.setText("")
                    binding.progressBarLogin.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<Login_Model?>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.no_connection),
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBarLogin.visibility = View.INVISIBLE
            }
        })
    }

    fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        Toast.makeText(
            applicationContext,
            getString(R.string.auth_success),
            Toast.LENGTH_SHORT
        ).show()
    }

}




