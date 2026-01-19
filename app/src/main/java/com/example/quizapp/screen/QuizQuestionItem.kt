package com.example.quizapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quizapp.model.QuizUiModel

@Composable
fun QuizQuestionItem(
    quiz: QuizUiModel,
    onClick: (String) -> Unit
) {
    Column {

        Text(
            quiz.data.question,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        listOf("A","B","C","D").forEach { option ->
            val text = when(option){
                "A" -> quiz.data.A
                "B" -> quiz.data.B
                "C" -> quiz.data.C
                else -> quiz.data.D
            }

            val bgColor = when {
                quiz.selected == null -> Color.LightGray
                quiz.selected == option && quiz.isCorrect == true -> Color.Green
                quiz.selected == option && quiz.isCorrect == false -> Color.Red
                quiz.data.answer == option -> Color.Green
                else -> Color.LightGray
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable(enabled = quiz.selected == null) {
                        onClick(option)
                    },
                colors = CardDefaults.cardColors(containerColor = bgColor)
            ) {
                Text(
                    text = "$option. $text",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
/*
quiz.data.option.forEach { (key, value) ->

    val bgColor = when {

        quiz.selected == null -> Color.LightGray
        quiz.selected == key && quiz.isCorrect == true -> Color.Green
        quiz.selected == key && quiz.isCorrect == false -> Color.Red
        quiz.data.answer == key -> Color.Green
        else -> Color.LightGray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(enabled = quiz.selected == null) {
                onClick(key)
            },
        colors = CardDefaults.cardColors(bgColor)
    ) {
        Text(
            text = "$key. $value",
            modifier = Modifier.padding(16.dp)
        )
    }
}
*/
