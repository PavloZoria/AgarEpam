package ua.com.epam.agar.hackathon.api.game.socket.ktor

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.isActive
import ua.com.epam.agar.hackathon.core.printLine

internal class WsClient(private val client: HttpClient) {
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

    suspend fun close(reason: CloseReason) = session?.let {
        printLine("close: $reason")
        it.close(reason)
        it.cancel(reason.message)
        session = null
        printLine("WebSocket closed!")
    }

    suspend fun send(message: Frame) {
        if (session?.isActive == true) {
            printLine("Send to socket:" + (message as? Frame.Text)?.readText())
            session?.send(message)
        }
    }

    @InternalCoroutinesApi
    suspend fun receive(onReceive: (input: Frame.Text) -> Unit) {
        session?.incoming?.consumeAsFlow()?.collect(object : FlowCollector<Frame> {
            override suspend fun emit(value: Frame) {
                // if (session?.incoming?.isClosedForReceive != true) {
                    printLine("Received from socket:" + (value as? Frame.Text)?.readText())
                    (value as? Frame.Text)?.let(onReceive)
                // }
            }
        })
    }
}
