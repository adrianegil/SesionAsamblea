package cu.desoft.sesionasamblea.ui.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.databinding.AssistanceActivityBinding
import cu.desoft.sesionasamblea.logic.Assistance
import cu.desoft.sesionasamblea.managers.AssistanceListManager
import cu.desoft.sesionasamblea.ui.notepad.AssistanceAdapter
import cu.desoft.sesionasamblea.utils.UserHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AssistanceActivity : AppCompatActivity() {

    lateinit var binding: AssistanceActivityBinding
    private var assistanceList: ArrayList<Assistance> = arrayListOf()
    private var assistanceListOut: ArrayList<Assistance> = arrayListOf()
    var currentDate: String = ""
    var token: String = "token 8ec51928bf8096226f3aba3f0cd00b6404feecee"
    var countPresent = 0
    var countAusent = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AssistanceActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAboutActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.recyclerListAssistance.layoutManager = LinearLayoutManager(this)
        currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        token = "token " + UserHelper.getToken(this)

        binding.swipeRefreshAssistanceList.setColorSchemeResources(
            R.color.white,
            R.color.white,
            R.color.blue
        )
        binding.swipeRefreshAssistanceList.isRefreshing = true
        binding.recyclerListAssistance.adapter = AssistanceAdapter(assistanceList)
        binding.recyclerListAssistance.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.run {
            swipeRefreshAssistanceList.setOnRefreshListener {
                onClickPresent()
                retrieveData()

            }
        }

        retrieveData()

        binding.cardPresent.setOnClickListener {
            onClickPresent()
        }
        binding.cardOut.setOnClickListener {
            onClickOut()
        }
    }


    private fun retrieveData() {
        try {
            binding.swipeRefreshAssistanceList.isRefreshing = true
            val assistanceManager = AssistanceListManager()
            val call: Call<JsonObject> = assistanceManager.getAssistance(currentDate, token)
            call.enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) =
                    if (null != response.body() && response.code() == 200) {
                        val present = response.body()!!.asJsonObject["results"].asJsonArray
                        binding.countPresents.text =
                            response.body()!!.asJsonObject["count"].asString
                        countPresent = response.body()!!.asJsonObject["count"].asInt;
                        assistanceList = java.util.ArrayList<Assistance>()
                        for (x in 0 until present.size()) {
                            val element = present[x]
                            val name = element.asJsonObject["nombre"].asString
                            val number = element.asJsonObject["folio"].asString
                            assistanceList.add(Assistance(name, number))
                        }
                        val adapter = AssistanceAdapter(assistanceList)
                        binding.recyclerListAssistance.adapter = adapter

                        retrieveDataOut()
                    } else {
                        binding.swipeRefreshAssistanceList.isRefreshing = false
                    }

                override fun onFailure(call: Call<JsonObject?>, throwable: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Problemas en la conexión",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.w("network", throwable.toString())
                    binding.swipeRefreshAssistanceList.isRefreshing = false

                }
            })
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Problemas en la conexión",
                Toast.LENGTH_SHORT
            ).show()
            binding.swipeRefreshAssistanceList.isRefreshing = false


        }
    }

    private fun retrieveDataOut() {

        try {
            binding.swipeRefreshAssistanceList.isRefreshing = true
            val assistanceManager = AssistanceListManager()
            val call: Call<JsonObject> = assistanceManager.getOut(currentDate, token)
            call.enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                    if (null != response.body() && response.code() == 200) {
                        val present = response.body()!!.asJsonObject["results"].asJsonArray
                        binding.countOut.text = response.body()!!.asJsonObject["count"].asString
                        countAusent = response.body()!!.asJsonObject["count"].asInt;
                        assistanceListOut = java.util.ArrayList<Assistance>()
                        for (x in 0 until present.size()) {
                            val element = present[x]
                            val name = element.asJsonObject["nombre"].asString
                            val number = element.asJsonObject["folio"].asString
                            assistanceListOut.add(Assistance(name, number))
                        }
                        val total = countPresent + countAusent
                        val porcent = countPresent * 100 / total
                        Log.w("Porcent", total.toString() + " " + porcent.toString())
                        binding.countTotalPercent.text = (porcent).toString() + "%"
                        binding.swipeRefreshAssistanceList.isRefreshing = false


                    } else
                        binding.swipeRefreshAssistanceList.isRefreshing = false
                }

                override fun onFailure(call: Call<JsonObject?>, throwable: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Problemas en la conexión",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.w("network", throwable.toString())
                    binding.swipeRefreshAssistanceList.isRefreshing = false

                }
            })
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Problemas en la conexión",
                Toast.LENGTH_SHORT
            ).show()
            binding.swipeRefreshAssistanceList.isRefreshing = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onClickPresent() {
        val adapter = AssistanceAdapter(assistanceList)
        binding.recyclerListAssistance.adapter = adapter
        binding.cardPresent.background = getDrawable(R.color.primary)
        binding.cardOut.background = getDrawable(R.color.white)
        binding.textPresent.setTextColor(getColor(R.color.white))
        binding.countPresents.setTextColor(getColor(R.color.white))
        binding.countOut.setTextColor(getColor(R.color.black))
        binding.textOut.setTextColor(getColor(R.color.black))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onClickOut() {
        val adapter = AssistanceAdapter(assistanceListOut)
        binding.recyclerListAssistance.adapter = adapter
        binding.cardPresent.background = getDrawable(R.color.white)
        binding.cardOut.background = getDrawable(R.color.red)
        binding.textPresent.setTextColor(getColor(R.color.black))
        binding.countPresents.setTextColor(getColor(R.color.black))
        binding.countOut.setTextColor(getColor(R.color.white))
        binding.textOut.setTextColor(getColor(R.color.white))
    }
}