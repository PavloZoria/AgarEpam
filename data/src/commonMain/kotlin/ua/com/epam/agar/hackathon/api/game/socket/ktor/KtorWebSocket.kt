package ua.com.epam.agar.hackathon.api.game.socket.ktor

import ua.com.epam.agar.hackathon.api.game.socket.WebSocket
import ua.com.epam.agar.hackathon.api.game.socket.WebSocketException
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ua.com.epam.agar.hackathon.core.entity.mapper.Mapper
import ua.com.epam.agar.hackathon.core.entity.mapper.mapFrom
import ua.com.epam.agar.hackathon.core.entity.mapper.mapToSafely
import ua.com.epam.agar.hackathon.core.printLine

internal class KtorWebSocket<Model>(
    private val host: String = "localhost",
    private val port: Int = DEFAULT_PORT,
    private val path: String = "/",
    private val modelMapper: SocketModelMapper<Model>,
) : WebSocket<Model> {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    override val inputEvents = MutableSharedFlow<Model?>(onBufferOverflow = BufferOverflow.SUSPEND, replay = 1)
    override val outputEvents: MutableSharedFlow<Frame?> = MutableSharedFlow()
    override val errorEvents = MutableSharedFlow<Exception?>()

    private var wsClient: WsClient = WsClient(HttpClient {
        install(WebSockets)
    })

    override suspend fun openSocket() {
        try {
            wsClient.connect(host = host, port = port, path = path)
            printLine("Socket session created!")

            coroutineScope.launch {
                wsClient.receive { message ->
                    // printLine("Received from socket:" + message.readText())
                    val value = modelMapper.mapToSafely(message)
                    inputEvents.tryEmit(value)
                    // println("WS Listening emitted: $tryEmit, value: $value")
                }
            }
        } catch (e: Exception) {
            println("WS Connection/Listening error:" + e.message)
            if (e is ClosedReceiveChannelException) {
                errorEvents.emit(WebSocketException("Disconnected", e))
            } else if (e is WebSocketException) {
                errorEvents.emit(WebSocketException("Unable to connect.", e))
            }

            coroutineScope.launch {
                println("Socket connection restart")
                delay(5_000)
                openSocket()
            }
        }
        printLine("Socket connection established!")
    }

    override suspend fun closeSocket(reason: String) {
        printLine("closeSocket: code: ${CloseReason.Codes.NORMAL.code}, reason: $reason")
        val closeReason = CloseReason(CloseReason.Codes.NORMAL.code, reason)
        wsClient.close(closeReason)
        coroutineScope.coroutineContext.cancelChildren()
        printLine("closeSocket: closed!")
    }

    override suspend fun sendMessage(value: Model) {
        // printLine("sendMessage: $value")
        wsClient.send(modelMapper.mapFrom(value))
    }
}

interface SocketModelMapper<T> : Mapper<Frame.Text, T>