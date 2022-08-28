package com.dv202.wildlifetrivia.models

data class QuestionDto (
    var question: String? = "",
    var answer: String? = "",
    var category: String? = "",
    var options: List<String>? = null
)
