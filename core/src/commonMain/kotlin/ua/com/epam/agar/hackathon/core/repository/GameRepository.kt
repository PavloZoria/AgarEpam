package ua.com.epam.agar.hackathon.core.repository

import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.game.config.GameConfig

interface GameRepository {
    suspend fun connectToRoom(room: String, isTraining: Boolean = false): GameConfig

    suspend fun mapState(): MapState
    suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState
}