package com.example.quizapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.quizapp.QuizViewModel
import com.example.quizapp.model.QuizIntent

@Composable
fun QuizScreen(
    modifier: Modifier= Modifier,
    viewModel: QuizViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val current = state.questions[state.currentIndex]


    if(state.quizFinished){
        ResultScreen(state.score, state.questions.size) {
            viewModel.onIntent(QuizIntent.Restart)
        }
        return
    }

    val pagerState = rememberPagerState(
        //initialPage = state.currentIndex,
        pageCount = { state.questions.size }
    )
    LaunchedEffect(state.currentIndex) {
        pagerState.animateScrollToPage(state.currentIndex)
    }

    Column(modifier.fillMaxSize().padding(16.dp)) {

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { index ->
            QuizQuestionItem(
                quiz = state.questions[index],
                onClick = {it->
                    viewModel.onIntent(
                        QuizIntent.SelectOption(index, it)
                    )
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            enabled = current.selected != null,
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onIntent(QuizIntent.Next) }
        ) {
            Text("Next")
        }
    }
}
