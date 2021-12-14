package com.ua.agar.io.hackathon.api.game.socket

import com.ua.agar.io.hackathon.api.game.socket.model.data.GameConfigModel
import com.ua.agar.io.hackathon.api.game.socket.model.data.DesiredCellsStateModel
import com.ua.agar.io.hackathon.api.game.socket.model.data.MapStateModel

internal interface GameDataRepository {

    suspend fun connectTransportToRoom(room: String, isTraining: Boolean = false): GameConfigModel
    suspend fun gameConfigFromTransport(): GameConfigModel

    suspend fun mapStateFromTransport(): MapStateModel
    suspend fun transportGameTurn(desiredCellsState: DesiredCellsStateModel?): MapStateModel

    suspend fun connectToTransport()
    suspend fun disconnectFromTransport(reason: String)
}