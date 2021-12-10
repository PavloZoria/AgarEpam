package com.ua.agar.io.hackathon.api.game.socket.ktor

import com.ua.agar.io.hackathon.api.game.socket.WebSocket
import com.ua.agar.io.hackathon.api.game.socket.WebSocketException
import com.ua.epam.agar.io.hackathon.core.entity.mapper.Mapper
import com.ua.epam.agar.io.hackathon.core.entity.mapper.mapToSafely
import com.ua.epam.agar.io.hackathon.core.printLine
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class WsClient(private val client: HttpClient) {
    var session: WebSocketSession? = null

    suspend fun connect(
        host: String = "localhost",
        port: Int = DEFAULT_PORT,
        path: String = "/",
    ) {
        session = client.webSocketSession(
            method = HttpMethod.Get,
            host = host,
            port = port,
            path = path
        )
    }

    suspend fun close(reason: CloseReason) {
        printLine("close: $reason")
        session?.close(reason)
        session = null
    }

    suspend fun send(message: Frame) {
        printLine("Send to socket:" + (message as? Frame.Text)?.readText())
        session?.send(message)
    }

    @OptIn(InternalCoroutinesApi::class)
    suspend fun receive(onReceive: (input: Frame.Text) -> Unit) {
        session?.incoming?.consumeAsFlow()?.collect(object : FlowCollector<Frame> {
            override suspend fun emit(value: Frame) {
                (value as? Frame.Text)?.let(onReceive) ?: run {
                    // printLine("Received from socket(not a text):$it")
                }
            }
        })
    }
}
