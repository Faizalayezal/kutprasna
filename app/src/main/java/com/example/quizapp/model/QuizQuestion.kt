package com.example.quizapp.model

data class QuizQuestion(
    val question:String,
  //  val option: Map<String, String> = emptyMap(),
    val A: String,
    val B: String,
    val C: String,
    val D: String,
    val answer:String,

)