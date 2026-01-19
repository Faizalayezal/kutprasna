package com.example.quizapp

import androidx.lifecycle.ViewModel
import com.example.quizapp.model.QuizIntent
import com.example.quizapp.model.QuizState
import com.example.quizapp.model.QuizUiModel
import com.example.quizapp.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state


    init {
        loadQuiz()
    }

    private fun loadQuiz() {

        val quiz = repository.loadQuizFromJson().map {
            QuizUiModel(it)
        }
        _state.value = QuizState(questions = quiz)

    }

    fun onIntent(intent: QuizIntent) {

        when (intent) {
            is QuizIntent.SelectOption -> selectOption(intent)
            QuizIntent.Finish -> finishQuiz()
            QuizIntent.Next -> nextQuestion()
            QuizIntent.Restart -> restartQuiz()
        }
    }

    private fun selectOption(intent: QuizIntent.SelectOption) {

        val currantState = _state.value
        val index = currantState.currentIndex

        val list = currantState.questions.toMutableList()
        val item = list[index]
        if (item.selected != null) return

        val correct = item.data.answer == intent.option
        list[index] = item.copy(
            selected = intent.option,
            isCorrect = correct
        )

        _state.value = currantState.copy(
            questions = list,
            score = if (correct) currantState.score + 1 else currantState.score
        )
    }

    private fun nextQuestion() {

        val next = _state.value.currentIndex + 1
        if (next < _state.value.questions.size) {
            _state.value = _state.value.copy(currentIndex = next)
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        _state.value = _state.value.copy(quizFinished = true)

    }

    private fun restartQuiz() {
        val quiz = repository.loadQuizFromJson().map {
            QuizUiModel(it)
        }

        _state.value = QuizState(
            questions = quiz,
            currentIndex = 0,
            score = 0,
            quizFinished = false
        )

    }
}