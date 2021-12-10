package com.ua.epam.agar.io.hackathon.core.repository

import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

interface GameRepository {
    suspend fun connectToRoom(room: String, isTraining: Boolean = false): GameConfig

    suspend fun mapState(): MapState
    suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState
}