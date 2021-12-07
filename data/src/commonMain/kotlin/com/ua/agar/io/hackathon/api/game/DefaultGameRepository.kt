package com.ua.agar.io.hackathon.api.game

import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig

class DefaultGameRepository constructor(
    private val wsGameRepository: WsGameRepository
) : GameRepository {

    override suspend fun connectToRoom(room: String): GameConfig = wsGameRepository.connectToRoom(room)

    override suspend fun mapState(): MapState = wsGameRepository.mapState()

    override suspend fun gameConfig(): GameConfig = wsGameRepository.gameConfig()

    override suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState =
        wsGameRepository.gameTurn(desiredCellsState)
}