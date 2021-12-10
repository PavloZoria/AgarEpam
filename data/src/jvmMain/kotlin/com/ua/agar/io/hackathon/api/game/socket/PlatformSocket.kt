package com.ua.agar.io.hackathon.api.game.socket

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import okhttp3.WebSocket

//Android
// internal actual class PlatformSocket actual constructor(url: String) {
//     private val socketEndpoint: String = url
//     private var webSocket: WebSocket? = null
//
//     actual fun openSocket(listener: PlatformSocketListener) {
//         val socketRequest = Request.Builder().url(socketEndpoint).build()
//         val webClient = OkHttpClient().newBuilder().build()
//         webSocket = webClient.newWebSocket(
//             socketRequest,
//             object : okhttp3.WebSocketListener() {
//                 override fun onOpen(webSocket: WebSocket, response: Response) = listener.onOpen()
//                 override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) = listener.onFailure(t)
//                 override fun onMessage(webSocket: WebSocket, text: String) = listener.onMessage(text)
//                 override fun onClosing(webSocket: WebSocket, code: Int, reason: String) = listener.onClosing(code, reason)
//                 override fun onClosed(webSocket: WebSocket, code: Int, reason: String) = listener.onClosed(code, reason)
//             }
//         )
//     }
//     actual fun closeSocket(code: Int, reason: String) {
//         webSocket?.close(code, reason)
//         webSocket = null
//     }
//     actual fun sendMessage(msg: String) {
//         webSocket?.send(msg)
//     }
// }

// internal actual class PlatformSocket actual constructor(url: String) {
//     private val socketEndpoint: String = url
//     private var webSocket: WebSocket? = null
//
//     private lateinit var inputEvents: MutableSharedFlow<Frame?>
//     private val outputEvents = MutableSharedFlow<Frame?>()
//
//     actual suspend fun openSocket(listener: PlatformSocketListener) {
//         val client = HttpClient {
//             install(WebSockets)
//         }
//         client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/chat") {
//             inputEvents = toIncomingFlow()
//             handleInputMessages()
//         }
//     }
//     actual suspend fun closeSocket(code: Int, reason: String) {
//         webSocket?.close(code, reason)
//         webSocket = null
//     }
//     actual suspend fun sendMessage(msg: String) {
//         outputEvents.emit(Frame.Text(msg))
//     }
//
//     private suspend fun DefaultClientWebSocketSession.handleInputMessages() {
//         outputEvents.onEach {
//             if (it != null) {
//                 when (it) {
//                     is Frame.Close -> {
//                         return@onEach
//                     }
//                     else -> {
//                         try {
//                             send(it)
//                         } catch (e: Exception) {
//                             println("Error while sending: " + e.localizedMessage)
//                             return@onEach
//                         }
//                     }
//                 }
//             }
//         }
//     }
// }
//
// suspend fun DefaultClientWebSocketSession.toIncomingFlow(): MutableSharedFlow<Frame?> {
//     val flow = MutableSharedFlow<Frame?>(1)
//     try {
//         for (message in incoming) {
//             flow.emit(message)
//         }
//     } catch (e: Exception) {
//         println("Error while receiving: " + e.localizedMessage)
//     }
//     return flow
// }