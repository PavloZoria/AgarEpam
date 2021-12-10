package com.ua.epam.agar.io.hackathon.core.repository

import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

class DefaultGameRepository constructor(
    private val gameRepository: GameRepository,
) : GameRepository {

    override suspend fun connectToRoom(room: String, isTraining: Boolean): GameConfig =
        gameRepository.connectToRoom(room, isTraining)

    override suspend fun mapState(): MapState = gameRepository.mapState()

    override suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState =
        gameRepository.gameTurn(desiredCellsState)
}