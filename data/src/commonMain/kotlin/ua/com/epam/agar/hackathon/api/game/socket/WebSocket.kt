package ua.com.epam.agar.hackathon.api.game.socket

import io.ktor.http.cio.websocket.Frame
import kotlinx.coroutines.flow.MutableSharedFlow

internal interface WebSocket<Model> {
    val inputEvents: MutableSharedFlow<Model?>
    val outputEvents: MutableSharedFlow<Frame?>
    val errorEvents: MutableSharedFlow<Exception?>
    suspend fun openSocket()
    suspend fun closeSocket(reason: String)
    suspend fun sendMessage(value: Model)
}

class WebSocketException(override val message: String, val originalException: Exception) : Exception()
