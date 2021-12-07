package com.ua.agar.io.hackathon.api.game.storage

import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

interface GameStorage {
    suspend fun gameConfig(gameConfig: GameConfig)
    suspend fun gameConfig(): GameConfig?

    suspend fun mapState(mapState: MapState)
    suspend fun mapState(): MapState?

    suspend fun gameTurn(desiredCellsState: DesiredCellsState?)

    suspend fun gameTurn(): DesiredCellsState?
}