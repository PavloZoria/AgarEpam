package com.ua.agar.io.hackathon.api.game.socket

import com.ua.agar.io.hackathon.data.mapper.config.GameConfigMapper
import com.ua.agar.io.hackathon.data.mapper.map_state.MapStateMapper
import com.ua.agar.io.hackathon.data.mapper.map_state.desired_state.DesiredCellStateMapper
import com.ua.epam.agar.io.hackathon.core.entity.main.DesiredCellsState
import com.ua.epam.agar.io.hackathon.core.entity.main.MapState
import com.ua.epam.agar.io.hackathon.core.game.config.GameConfig
import com.ua.epam.agar.io.hackathon.core.repository.GameRepository

internal class GameWebInteractor(
    private val gameDataRepository: GameDataRepository,
    private val gameConfigMapper: GameConfigMapper = GameConfigMapper(),
    private val mapStateMapper: MapStateMapper = MapStateMapper(),
    private val desiredCellStateMapper: DesiredCellStateMapper = DesiredCellStateMapper()
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