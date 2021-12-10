package com.ua.agar.io.hackathon.api.game.socket

import com.ua.agar.io.hackathon.api.game.storage.Storage
import com.ua.agar.io.hackathon.api.game.socket.model.data.GameConfigModel
import com.ua.agar.io.hackathon.api.game.socket.model.data.DesiredCellsStateModel
import com.ua.agar.io.hackathon.api.game.socket.model.data.MapStateModel

class GameDataCombinedRepository(
    private val gameDataRepository: GameDataRepository,
    private val mapStorage: Storage<MapStateModel>,
) : GameDataRepository {
    override suspend fun connectTransportToRoom(room: String, isTraining: Boolean): GameConfigModel =
        gameDataRepository.connectTransportToRoom(room)

    override suspend fun gameConfigFromTransport(): GameConfigModel =
        gameDataRepository.gameConfigFromTransport()//TODO need to be stored

    override suspend fun mapStateFromTransport(): MapStateModel {
        val optimizedData = gameDataRepository.mapStateFromTransport()
        mapStorage.set(optimizedData)
        return mapStorage.get()
    }

    override suspend fun transportGameTurn(desiredCellsState: DesiredCellsStateModel?): MapStateModel {
        val optimizedData = gameDataRepository.transportGameTurn(desiredCellsState)
        mapStorage.set(optimizedData)
        val get = mapStorage.get()
        println("GameDataCombinedRepository -> optimizedData: ${optimizedData.cellsOnMap.size}, data: ${get.cellsOnMap.size}")
        return get
    }

    override suspend fun connectToTransport() {
        mapStorage.invalidate()
        gameDataRepository.connectToTransport()
    }

    override suspend fun disconnectFromTransport(reason: String) {
        mapStorage.invalidate()
        gameDataRepository.disconnectFromTransport(reason)
    }
}