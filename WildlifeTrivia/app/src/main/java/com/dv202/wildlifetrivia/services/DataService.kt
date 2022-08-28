package com.dv202.wildlifetrivia.services


import android.util.Log
import com.dv202.wildlifetrivia.models.QuestionDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

object DataService {

    private var currentQuestion: QuestionDto? = null
    private var questionIndex: Int = 0
    private var totalQuestions: Int = 0
    private var totalPoints: Int = 0
    private var categorySelected: String = ""

    private var playerName: String = ""
    private var questions: List<QuestionDto>? = null
    private var answers: MutableList<String> = mutableListOf()

    fun setPlayerName(name: String) {
        playerName = name
    }

    fun getPlayerName(): String {
        return playerName
    }

    fun setCategory(cat: String) {
        categorySelected = cat
    }

    fun getCategory(): String {
        return categorySelected
    }

    fun getQuestions(): List<QuestionDto>? {
        this.totalQuestions = this.questions!!.count()
        return this.questions
    }

    fun getFaunaQuestions(): List<QuestionDto>? {
        this.questions = questions?.filter { question -> question.category == "animals" }
        this.totalQuestions = this.questions!!.count()
        return this.questions

    }

    fun getFLoraQuestions(): List<QuestionDto>? {
        this.questions = questions?.filter { question -> question.category == "plants" }
        this.totalQuestions = this.questions!!.count()
        return this.questions
    }

    fun getNextQuestion(): QuestionDto? {

        if (this.questionIndex < totalQuestions) {
            this.currentQuestion = questions!![this.questionIndex]
            questionIndex++
        } else {
            return null
        }

        return currentQuestion
    }

    fun getAnswers(): List<String>? {
        return this.answers
    }

    fun setAnswer(input: String) {
        this.answers?.add(input)
    }


    fun checkNextQuestionType(): Boolean? {
        if (this.questionIndex < totalQuestions) {
            val nextQuestion = questions?.get(this.questionIndex)
            return nextQuestion?.options != null
        } else {
            return null
        }
    }

    fun getTotalPoints(): Int {
        return this.totalPoints
    }

    fun incrementPoints() {
        this.totalPoints = this.totalPoints + 10
    }

    fun getDataFromServer() {
        val db = Firebase.firestore

        db.collection("questions").get().addOnSuccessListener { documents ->
            this.setQuestions(documents.toObjects())
        }
    }

    fun reset() {
        this.currentQuestion = null
        this.questionIndex = 0
        this.totalQuestions = 0
        this.totalPoints = 0
        this.categorySelected = ""

        this.playerName = ""
        this.questions  = null
        this.answers = mutableListOf()

        getDataFromServer()

        // Op jou back to home screen
    }

    private fun setQuestions(questions: List<QuestionDto>?) {
        this.questions = questions
    }
}