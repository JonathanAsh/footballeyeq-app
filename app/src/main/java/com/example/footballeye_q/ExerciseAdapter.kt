package com.example.footballeye_q

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(private var exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)
        val exerciseAges: TextView = itemView.findViewById(R.id.exerciseAges)
        val exerciseCategories: TextView = itemView.findViewById(R.id.exerciseCategories)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = exercises[position]
                    val context: Context = itemView.context
                    val intent = Intent(context, ExerciseDetailActivity::class.java)
                    intent.putExtra("exerciseId", exercise._id) // Pass the exercise ID
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.exerciseName.text = exercise.name
        holder.exerciseAges.text = exercise.ages
        holder.exerciseCategories.text = exercise.categories.joinToString(", ")
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    fun updateExercises(newExercises: List<Exercise>) {
        exercises = newExercises
        notifyDataSetChanged()
    }
}
