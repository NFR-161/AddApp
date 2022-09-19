package com.exampleone.addapp.domain.usecase

import com.exampleone.addapp.domain.entity.GameSettings
import com.exampleone.addapp.domain.entity.Level
import com.exampleone.addapp.domain.repository.GameRepository


class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}