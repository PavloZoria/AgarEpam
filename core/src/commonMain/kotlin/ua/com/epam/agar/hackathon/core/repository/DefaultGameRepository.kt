package ua.com.epam.agar.hackathon.core.repository

import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.game.config.GameConfig

class DefaultGameRepository constructor(
    private val gameRepository: GameRepository,
) : GameRepository {

    override suspend fun connectToRoom(room: String, isTraining: Boolean): GameConfig =
        gameRepository.connectToRoom(room, isTraining)

    override suspend fun mapState(): MapState = gameRepository.mapState()

    override suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState =
        gameRepository.gameTurn(desiredCellsState)
}