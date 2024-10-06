package com.example.footballeye_q

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import okhttp3.*
import java.io.IOException

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = ExerciseAdapter(emptyList()) // Initialize with an empty list
        recyclerView.adapter = exerciseAdapter

        // Find SwipeRefreshLayout by ID
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        // Set up the SwipeRefreshLayout listener
        swipeRefreshLayout.setOnRefreshListener {
            queryLocalApi("http://10.0.2.2:3000/user")
        }

        // Load exercises on app start
        queryLocalApi("http://10.0.2.2:3000/user")
    }

    private fun queryLocalApi(url: String) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "API Request Failed", Toast.LENGTH_SHORT).show()
                    swipeRefreshLayout.isRefreshing = false // Stop the spinner
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    swipeRefreshLayout.isRefreshing = false // Stop the spinner
                    if (response.isSuccessful) {
                        val responseData = response.body?.string()
                        // Here, parse the response data and update your RecyclerView
                        // Assuming the JSON contains a list of exercises
                        val exerciseType = object : TypeToken<List<Exercise>>() {}.type
                        val exercises: List<Exercise> = Gson().fromJson(responseData, exerciseType)
                        exerciseAdapter.updateExercises(exercises) // Update your adapter with the new data
                        Toast.makeText(this@MainActivity, "Loaded ${exercises.size} exercises", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Error: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
