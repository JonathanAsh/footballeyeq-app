package com.example.footballeye_q

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)

        // Initialize the RecyclerView and Adapter
        exerciseAdapter = ExerciseAdapter(emptyList())
        recyclerView.adapter = exerciseAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up SwipeRefreshLayout listener
        swipeRefreshLayout.setOnRefreshListener {
            queryLocalApi("http://10.0.2.2:3000/user/exercises") // Replace with your actual API URL
        }

        // Call API on initial load
        queryLocalApi("http://10.0.2.2:3000/user/exercises")
    }

    private fun queryLocalApi(url: String) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "API Request Failed", Toast.LENGTH_SHORT).show()
                    swipeRefreshLayout.isRefreshing = false // Stop the spinner on failure
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    swipeRefreshLayout.isRefreshing = false // Stop the spinner
                    if (response.isSuccessful) {
                        val responseData = response.body?.string()
                        val exerciseType = object : TypeToken<List<Exercise>>() {}.type
                        val exercises: List<Exercise> = Gson().fromJson(responseData, exerciseType)
                        exerciseAdapter.updateExercises(exercises) // Make sure you have this method in your adapter
                    } else {
                        Toast.makeText(this@MainActivity, "Error: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
