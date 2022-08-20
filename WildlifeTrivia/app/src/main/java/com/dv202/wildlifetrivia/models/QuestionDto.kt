package com.dv202.wildlifetrivia.models

data class QuestionDto(
    val question: String? = null,
    val answer: String? = null,
    val options: List<String>? = null,
    val type: String? = null,
)
