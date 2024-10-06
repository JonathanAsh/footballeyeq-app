package com.example.footballeye_q

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class ExerciseDetailActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var exerciseDescription: TextView
    private lateinit var exerciseCategories: TextView
    private lateinit var exerciseAges: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        backButton = findViewById(R.id.backButton)
        exerciseDescription = findViewById(R.id.exerciseDescription)
        exerciseCategories = findViewById(R.id.exerciseCategories)
        exerciseAges = findViewById(R.id.exerciseAges)

        val exerciseId = intent.getStringExtra("exerciseId")

        // Handle the back button click
        backButton.setOnClickListener {
            finish()
        }

        // Fetch the details of the exercise using the exerciseId (you can implement a method to retrieve details)
        // Example: Use the same API or a new endpoint to fetch the exercise details
        fetchExerciseDetails(exerciseId)
    }

    private fun fetchExerciseDetails(exerciseId: String?) {
        if (exerciseId != null) {
            val url = "http://10.0.2.2:3000/exercise/$exerciseId" // Use the emulator IP to access the local API
            val request = Request.Builder().url(url).build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@ExerciseDetailActivity, "Failed to fetch exercise details", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    // Ensure you're handling the response on the correct thread
                    val responseData = response.body?.string() // This should be fine because it's on the callback thread

                    runOnUiThread {
                        if (response.isSuccessful && responseData != null) {
                            // Parse the JSON response
                            val exercise = Gson().fromJson(responseData, Exercise::class.java)
                            exerciseDescription.text = exercise.description
                            exerciseAges.text = "Ages: ${exercise.ages}"
                            exerciseCategories.text = "Categories: ${exercise.categories.joinToString(", ")}"
                        } else {
                            Toast.makeText(this@ExerciseDetailActivity, "Error: ${response.code}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}
