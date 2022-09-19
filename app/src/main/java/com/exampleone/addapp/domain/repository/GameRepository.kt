package com.exampleone.addapp.domain.repository

import com.exampleone.addapp.domain.entity.GameSettings
import com.exampleone.addapp.domain.entity.Level
import com.exampleone.addapp.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}