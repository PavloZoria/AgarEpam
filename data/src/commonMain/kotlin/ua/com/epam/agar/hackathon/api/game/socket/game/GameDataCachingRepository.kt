package ua.com.epam.agar.hackathon.api.game.socket.game

import ua.com.epam.agar.hackathon.api.game.socket.caching.CachingGameDataRepository
import ua.com.epam.agar.hackathon.api.game.socket.caching.RemoteGameDataRepository
import ua.com.epam.agar.hackathon.api.game.socket.game.tick.GameTickHandler
import ua.com.epam.agar.hackathon.api.game.storage.Storage
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.GameConfigModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.DesiredCellsStateModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.data.game.TickModel

internal class GameDataCachingRepository(
    private val gameDataRepository: RemoteGameDataRepository,
    private val mapStorage: Storage<MapStateModel>,
    private val tickHandler: GameTickHandler
) : CachingGameDataRepository {
    override suspend fun connectTransportToRoom(room: String, isTraining: Boolean): GameConfigModel =
        gameDataRepository.connectTransportToRoom(room)

    override suspend fun gameConfigFromTransport(): GameConfigModel =
        gameDataRepository.gameConfigFromTransport()

    override suspend fun mapStateFromTransport(): MapStateModel {
        val optimizedData = gameDataRepository.mapStateFromTransport()
        mapStorage.set(optimizedData)
        val mapStateModel = mapStorage.get()
        tickHandler.sendTick(TickModel(mapStateModel.tickNumber))
        return mapStateModel
    }

    override suspend fun transportGameTurn(desiredCellsState: DesiredCellsStateModel?): MapStateModel {
        val optimizedData = gameDataRepository.transportGameTurn(desiredCellsState)
        mapStorage.set(optimizedData)
        val mapStateModel = mapStorage.get()
        tickHandler.sendTick(TickModel(mapStateModel.tickNumber))
        // println("GameDataCombinedRepository -> optimizedData: ${optimizedData.cellsOnMap.size}, data: ${get.cellsOnMap.size}")
        return mapStateModel
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