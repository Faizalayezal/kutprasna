package com.example.quizapp.model

data class QuizState(
    val questions: List<QuizUiModel> = emptyList(),
    val currentIndex: Int = 0,
    val score: Int = 0,
    val quizFinished: Boolean = false
)
