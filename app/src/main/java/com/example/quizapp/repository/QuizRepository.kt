package com.example.quizapp.repository

import android.content.Context
import com.example.quizapp.R
import com.example.quizapp.model.QuizQuestion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val context: Context
) {
    fun loadQuizFromJson():List<QuizQuestion>{

        val inputStream = context.resources.openRawResource(R.raw.questions)
        val json = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<QuizQuestion>>() {}.type

        return Gson().fromJson(json, type)
    }
}