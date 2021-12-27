package ua.com.epam.agar.hackathon.api.game.socket.game

import ua.com.epam.agar.hackathon.api.game.socket.caching.CachingGameDataRepository
import ua.com.epam.agar.hackathon.core.entity.main.DesiredCellsState
import ua.com.epam.agar.hackathon.core.entity.main.MapState
import ua.com.epam.agar.hackathon.core.game.config.GameConfig
import ua.com.epam.agar.hackathon.core.repository.GameRepository
import ua.com.epam.agar.hackathon.data.mapper.config.GameConfigMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.MapStateMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state.DesiredCellStateMapper

internal class GameWebSocketInteractor(
    private val gameDataRepository: CachingGameDataRepository,
    private val gameConfigMapper: GameConfigMapper,
    private val mapStateMapper: MapStateMapper,
    private val desiredCellStateMapper: DesiredCellStateMapper
) : GameRepository {
    override suspend fun connectToRoom(room: String, isTraining: Boolean): GameConfig {
        val item = gameDataRepository.connectTransportToRoom(room)
        return gameConfigMapper.mapFrom(item)
    }

    override suspend fun mapState(): MapState {
        val mapState = gameDataRepository.mapStateFromTransport()
        return mapStateMapper.mapFrom(mapState)
    }

    override suspend fun gameTurn(desiredCellsState: DesiredCellsState?): MapState {
        val mapState =
            gameDataRepository.transportGameTurn(desiredCellsState?.let { desiredCellStateMapper.mapTo(desiredCellsState) })
        // println("gameTurn -> cellsOnMap: ${mapState.cellsOnMap.size}")
        return mapStateMapper.mapFrom(mapState)
    }
}