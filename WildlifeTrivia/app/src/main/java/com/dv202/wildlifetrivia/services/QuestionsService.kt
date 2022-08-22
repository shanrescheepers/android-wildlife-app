package com.dv202.wildlifetrivia.services

import com.dv202.wildlifetrivia.models.QuestionDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object QuestionsService {

    private var questions: List<QuestionDto>? = null
    private var playerName: String = ""

    fun getQuestions(): List<QuestionDto>? {
        if (this.questions == null) {
            return getDataFromServer()
        } else {
            return this.questions
        }
    }

    fun getFaunaQuestions(): List<QuestionDto>? {
        return getQuestions()?.filter { question -> question.type == "Fauna" }
    }

    fun getFloraQuestions(): List<QuestionDto>? {
        return getQuestions()?.filter { question -> question.type == "Flora" }
    }

    fun setPlayerName(name: String) {
        this.playerName = name
    }

    fun getPlayerName(): String {
        return this.playerName
    }

    private fun getDataFromServer() : List<QuestionDto>? {
        val db = Firebase.firestore
        val questionData : MutableList<QuestionDto>? = null

        db.collection("questions").get().addOnSuccessListener { questions ->
            for (question in questions) {
                val question = question.toObject<QuestionDto>()
                questionData?.add(question)
            }
        }

        return questionData
    }
}