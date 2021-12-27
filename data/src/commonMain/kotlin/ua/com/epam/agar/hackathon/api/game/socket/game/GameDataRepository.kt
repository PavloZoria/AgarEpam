package ua.com.epam.agar.hackathon.api.game.socket.game

import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.GameConfigModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.DesiredCellsStateModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel

internal interface GameDataRepository {

    suspend fun connectTransportToRoom(room: String, isTraining: Boolean = false): GameConfigModel
    suspend fun gameConfigFromTransport(): GameConfigModel

    suspend fun mapStateFromTransport(): MapStateModel
    suspend fun transportGameTurn(desiredCellsState: DesiredCellsStateModel?): MapStateModel

    suspend fun connectToTransport()
    suspend fun disconnectFromTransport(reason: String)
}