package com.example.footballeye_q

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Exercise4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail4)

        // Set up the Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Hide the title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Enable the back arrow in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up start and stop buttons
        val startButton = findViewById<Button>(R.id.start_button)
        val stopButton = findViewById<Button>(R.id.stop_button)

        // Handle Start button click
        startButton.setOnClickListener {
            // Start exercise logic here
            Log.d("ExerciseDetailActivity", "Start button clicked")
        }

        // Handle Stop button click
        stopButton.setOnClickListener {
            // Stop exercise logic here
            Log.d("ExerciseDetailActivity", "Stop button clicked")
        }
    }

    // Handle the action bar back button click
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Finish the current activity and go back to the previous screen
                finish() // This will close the current activity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
