package ua.com.epam.agar.hackathon.api.game.socket.game.tick

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import ua.com.epam.agar.hackathon.api.game.socket.WebSocket
import ua.com.epam.agar.hackathon.api.game.socket.model.WebSocketKey
import ua.com.epam.agar.hackathon.api.game.socket.model.WebSocketModel
import ua.com.epam.agar.hackathon.data.game.TickModel

internal class DefaultGameTickHandler(private val webSocket: WebSocket<WebSocketModel>) : GameTickHandler {
    override suspend fun sendTick(tick: TickModel) {
        webSocket.sendMessage(WebSocketModel(WebSocketKey.DATA_RECEIVED.key, Json.encodeToJsonElement(tick)))
    }
}