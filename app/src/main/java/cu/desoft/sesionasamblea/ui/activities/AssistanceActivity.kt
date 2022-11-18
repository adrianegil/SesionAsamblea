package cu.desoft.sesionasamblea.ui.activitie

import android.view.View

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
    var token: String = "8ec51928bf8096226f3aba3f0cd00b6404feecee"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AssistanceActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAboutActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_list_assistance)
        val recyclerviewOut = findViewById<RecyclerView>(R.id.recycler_list_out)
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
        binding.recyclerListAssistance?.adapter = AssistanceAdapter(assistanceList)
        binding.recyclerListAssistance?.layoutManager = LinearLayoutManager(
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
        retrieveDataOut(dataOut,recyclerviewOut)

        binding.cardPresent.setOnClickListener {
                binding.recyclerListAssistance?.visibility= View.VISIBLE
                binding.recyclerListOut?.visibility=View.GONE
           }
        binding.cardOut.setOnClickListener { retrieveDataOut(data,recyclerview) }
    }


    private fun retrieveData(data: ArrayList<Assistance>, recyclerview: RecyclerView) {

        try {
            binding.swipeRefreshAssistanceList.isRefreshing = true
            val assistanceManager = AssistanceListManager()
            val call: Call<JsonArray> = assistanceManager.getAssistance(currentDate, token)
            call.enqueue(object : Callback<JsonArray?> {
                override fun onResponse(call: Call<JsonArray?>, response: Response<JsonArray?>) {
                    if (null != response.body() && response.code() != 200) {
                        val present = response.body()!!.asJsonObject["results"].asJsonArray
                        assistanceList = java.util.ArrayList<Assistance>()
                        for (x in 0 until present.size()) {
                            val element = present[x]
                            val name = element.asJsonObject["nombre"].asString
                            val number = element.asJsonObject["folio"].asString
                            data.add(Assistance(name, number))

                        }


                        val adapter = AssistanceAdapter(data)
                        recyclerview.adapter = adapter
                    }
                    else
                    binding.swipeRefreshAssistanceList.isRefreshing = false
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
                    if (null != response.body() && response.code() != 200) {
                        val present = response.body()!!.asJsonObject["results"].asJsonArray
                        assistanceListOut = java.util.ArrayList<Assistance>()
                        for (x in 0 until present.size()) {
                            val element = present[x]
                            val name = element.asJsonObject["nombre"].asString
                            val number = element.asJsonObject["folio"].asString
                            data.add(Assistance(name, number))

                        }


                        val adapter = AssistanceAdapter(data)
                        recyclerview.adapter = adapter
                    }
                    else
                        binding.swipeRefreshAssistanceList.isRefreshing = false
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