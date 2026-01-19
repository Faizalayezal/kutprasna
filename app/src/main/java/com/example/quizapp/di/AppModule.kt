package com.example.quizapp.di

import android.app.Application
import android.content.Context
import com.example.quizapp.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(app: Application): Context = app

    @Provides
    fun provideQuizRepository(
        context: Context
    ): QuizRepository {
        return QuizRepository(context)
    }

}