package com.ua.agar.io.hackathon.api.game.socket

import com.ua.agar.io.hackathon.api.game.socket.model.WebSocketKey
import com.ua.agar.io.hackathon.api.game.socket.model.WebSocketModel
import com.ua.agar.io.hackathon.api.game.socket.model.parseEntity
import com.ua.agar.io.hackathon.api.game.socket.model.data.Room
import com.ua.agar.io.hackathon.api.game.socket.model.data.GameConfigModel
import com.ua.agar.io.hackathon.api.game.socket.model.data.DesiredCellsStateModel
import com.ua.agar.io.hackathon.api.game.socket.model.data.MapStateModel
import com.ua.epam.agar.io.hackathon.core.printLine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

internal class GameWebSocketAsAPI(
    private val webSocket: WebSocket<WebSocketModel>,
) : GameDataRepository {

    private val mapState: Flow<MapStateModel> = webSocket
        .parseEntity(WebSocketKey.GAME_DATA) { valueJson ->
            // printLine("mapState: $valueJson")
            Json.decodeFromJsonElement(valueJson)
        }

    private val gameConfig: Flow<GameConfigModel> = webSocket
        .parseEntity(WebSocketKey.GAME_CONFIG) {
            Json.decodeFromJsonElement(it)
        }

    // init {
    //     webSocket
    //         .parseEntity(WebSocketKey.GAME_CONFIG) { valueJson ->
    //             Json.decodeFromJsonElement<MapStateModel>(valueJson)
    //         }.onEach { value ->
    //             mapState.tryEmit(value)
    //         }
    // }

    override suspend fun connectTransportToRoom(room: String, isTraining: Boolean): GameConfigModel {
        // printLine("connectToRoom: $room")
        send(WebSocketModel(WebSocketKey.CONNECT_TO_ROOM.key, Json.encodeToJsonElement(Room(room, isTraining))))
        val gameConfigModel = gameConfig.first()
        // printLine("connectToRoom received: $room")
        return gameConfigModel
    }

    override suspend fun gameConfigFromTransport(): GameConfigModel {
        // printLine("gameConfigFromTransport...")
        send(WebSocketModel(WebSocketKey.GAME_CONFIG.key, null))
        return gameConfig.first()
    }

    override suspend fun mapStateFromTransport(): MapStateModel {
        // printLine("mapStateFromTransport...")
        // val data = WebSocketModel(WebSocketKey.PLAYER_ACTION.key, Json.encodeToJsonElement(DesiredCellsStateModel()))
        // send(data)
        return mapState.first()
    }

    override suspend fun transportGameTurn(desiredCellsState: DesiredCellsStateModel?): MapStateModel {
        // printLine("transportGameTurn: $desiredCellsState")
        if (desiredCellsState != null) {
            val data =
                WebSocketModel(WebSocketKey.PLAYER_ACTION.key, Json.encodeToJsonElement(desiredCellsState))
            send(data)
        }
        return mapState.first()
    }

    override suspend fun connectToTransport() = webSocket.openSocket()
    override suspend fun disconnectFromTransport(reason: String) = webSocket.closeSocket(reason)

    private suspend fun send(data: WebSocketModel) = webSocket.sendMessage(data)
}