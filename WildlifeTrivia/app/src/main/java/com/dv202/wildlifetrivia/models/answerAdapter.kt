package com.dv202.wildlifetrivia.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dv202.wildlifetrivia.R

internal class AnswerAdapter(private var questions: List<QuestionDto>, private var answers: List<String>): RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    inner class AnswerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var questionText: TextView = view.findViewById(R.id.questionText)
        var isCorrect: TextView = view.findViewById(R.id.correctIncorrect)
        var answerText: TextView = view.findViewById(R.id.correct)
        var inputText: TextView = view.findViewById(R.id.user)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return AnswerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val question = questions[position]
        val answer = answers[position]

        holder.questionText.text = question.question
        holder.answerText.text = question.answer
        holder.inputText.text = answer

        if (question.answer.toString().lowercase() == answer.lowercase()) {
            holder.isCorrect.text = "CORRECT"
        } else {
            holder.isCorrect.text = "INCORRECT"
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}