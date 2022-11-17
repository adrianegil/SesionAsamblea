package cu.desoft.sesionasamblea.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.gson.JsonArray
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.databinding.AssistanceActivityBinding
import cu.desoft.sesionasamblea.logic.Assistance
import cu.desoft.sesionasamblea.managers.AssistanceListManager
import cu.desoft.sesionasamblea.ui.notepad.AssistanceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AssistanceActivity : AppCompatActivity() {

    lateinit var binding: AssistanceActivityBinding
    private var assistanceList: ArrayList<Assistance> = arrayListOf()
    private var assistanceListOut: ArrayList<Assistance> = arrayListOf()
    var currentDate: String = ""
    var token: String = "15fa1506e3f908fa9c89652858aa80e50aea3920"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AssistanceActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAboutActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_lis_assistance)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<Assistance>()
        val dataOut=ArrayList<Assistance>()
        currentDate= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // This loop will create 20 Views containing
        // the image with the count of view


        binding.swipeRefreshAssistanceList.setColorSchemeResources(
            R.color.white,
            R.color.white,
            R.color.blue
        )
        binding.swipeRefreshAssistanceList.isRefreshing = true
        binding.recyclerLisAssistance.adapter = AssistanceAdapter(assistanceList)
        binding.recyclerLisAssistance.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.swipeRefreshAssistanceList.setOnRefreshListener(OnRefreshListener {
            retrieveData(
                data,
                recyclerview
            )
        })

        retrieveData(data, recyclerview)

        binding.cardPresent.setOnClickListener { retrieveData(data,recyclerview) }
        binding.cardOut.setOnClickListener { retrieveDataOut(data,recyclerview) }
    }


    private fun retrieveData(data: ArrayList<Assistance>, recyclerview: RecyclerView) {

        try {
            binding.swipeRefreshAssistanceList.isRefreshing = true
            val assistanceManager = AssistanceListManager()
            val call: Call<JsonArray> = assistanceManager.getAssistance(currentDate, "15fa1506e3f908fa9c89652858aa80e50aea3920")
            call.enqueue(object : Callback<JsonArray?> {
                override fun onResponse(call: Call<JsonArray?>, response: Response<JsonArray?>) {
                    if (null != response.body()) {
                        val jewls = response.body()!!.asJsonArray
                        assistanceList = java.util.ArrayList<Assistance>()
                        for (x in 0 until jewls.size()) {
                            val element = jewls[x]

                            val name = element.asJsonObject["username"].asString
                            val number = element.asJsonObject["email"].asString
                            data.add(Assistance(name, number))

                        }

                        binding.swipeRefreshAssistanceList.isRefreshing = false
                        val adapter = AssistanceAdapter(data)
                        recyclerview.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<JsonArray?>, throwable: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Problemas en la conexión" + throwable.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.w("network",throwable.toString())
                    binding.swipeRefreshAssistanceList.isRefreshing = false

                }
            })
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Problemas en la conexión" ,
                Toast.LENGTH_SHORT
            ).show()
            binding.swipeRefreshAssistanceList.isRefreshing = false


        }
    }

    private fun retrieveDataOut(data: ArrayList<Assistance>, recyclerview: RecyclerView) {

        try {
            binding.swipeRefreshAssistanceList.isRefreshing = true
            val assistanceManager = AssistanceListManager()
            val call: Call<JsonArray> = assistanceManager.getOut(currentDate, token)
            call.enqueue(object : Callback<JsonArray?> {
                override fun onResponse(call: Call<JsonArray?>, response: Response<JsonArray?>) {
                    if (null != response.body()) {
                        val jewls = response.body()!!.asJsonArray
                        assistanceList = java.util.ArrayList<Assistance>()
                        for (x in 0 until jewls.size()) {
                            val element = jewls[x]

                            val name = element.asJsonObject["username"].asString
                            val number = element.asJsonObject["email"].asString
                            data.add(Assistance(name, number))

                        }

                        binding.swipeRefreshAssistanceList.isRefreshing = false
                        val adapter = AssistanceAdapter(data)
                        recyclerview.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<JsonArray?>, throwable: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Problemas en la conexión" + throwable.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.w("network",throwable.toString())
                    binding.swipeRefreshAssistanceList.isRefreshing = false

                }
            })
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Problemas en la conexión" ,
                Toast.LENGTH_SHORT
            ).show()
            binding.swipeRefreshAssistanceList.isRefreshing = false


        }
    }
}