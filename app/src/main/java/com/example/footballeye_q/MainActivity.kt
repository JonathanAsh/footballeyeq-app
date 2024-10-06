package com.example.footballeye_q

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finding the buttons from the layout
        val exercise1Button = findViewById<Button>(R.id.exercise1Button)
        val exercise2Button = findViewById<Button>(R.id.exercise2Button)
        val exercise3Button = findViewById<Button>(R.id.exercise3Button)
        val exercise4Button = findViewById<Button>(R.id.exercise4Button)
        val exercise5Button = findViewById<Button>(R.id.exercise5Button)
        val exercise6Button = findViewById<Button>(R.id.exercise6Button)


        // Setting click listener for Exercise 1 button
        exercise1Button.setOnClickListener {
            val intent = Intent(this, ExerciseDetailActivity::class.java)
            intent.putExtra("exercise", "Exercise 1") // Passing the exercise name
            startActivity(intent)
        }

        // Setting click listener for Exercise 2 button
        exercise2Button.setOnClickListener {
            val intent = Intent(this, Exercise2::class.java)
            intent.putExtra("exercise", "Exercise 2") // Passing the exercise name
            startActivity(intent)
        }

        // Setting click listener for Exercise 3 button
        exercise3Button.setOnClickListener {
            val intent = Intent(this, Exercise3::class.java)
            intent.putExtra("exercise", "Exercise 3") // Passing the exercise name
            startActivity(intent)
        }

        // Setting click listener for Exercise 4 button
        exercise4Button.setOnClickListener {
            val intent = Intent(this, Exercise4::class.java)
            intent.putExtra("exercise", "Exercise 4") // Passing the exercise name
            startActivity(intent)
        }

        // Setting click listener for Exercise 5 button
        exercise5Button.setOnClickListener {
            val intent = Intent(this, Exercise5::class.java)
            intent.putExtra("exercise", "Exercise 5") // Passing the exercise name
            startActivity(intent)
        }

        // Setting click listener for Exercise 6 button
        exercise6Button.setOnClickListener {
            val intent = Intent(this, Exercise6::class.java)
            intent.putExtra("exercise", "Exercise 6") // Passing the exercise name
            startActivity(intent)
        }
    }
}
