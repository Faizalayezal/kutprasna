package com.example.quizapp.model

sealed class QuizIntent {

    data class SelectOption(
        val index: Int,
        val option: String
    ) : QuizIntent()

    object Next : QuizIntent()

    object Finish : QuizIntent()
    object Restart : QuizIntent()
}
