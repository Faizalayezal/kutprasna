package com.example.quizapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class QuizUiModel(
    val data: QuizQuestion,
    val selected: String?=null,
    val isCorrect: Boolean?=null
)