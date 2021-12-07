package com.ua.agar.io.hackathon.api

import com.ua.agar.io.hackathon.api.game.DefaultGameRepository
import com.ua.agar.io.hackathon.api.game.WsGameRepository
import com.ua.agar.io.hackathon.data.mapper.CellConfigMapper
import com.ua.agar.io.hackathon.data.mapper.FoodConfigMapper
import com.ua.agar.io.hackathon.data.mapper.GameConfigMapper
import com.ua.agar.io.hackathon.data.mapper.MapConfigMapper
import com.ua.agar.io.hackathon.serialization.ConfiguredProtoBuf
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.network.selector.SelectorManager
import io.ktor.util.InternalAPI
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.keepalive.KeepAlive
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import io.rsocket.kotlin.transport.ktor.TcpClientTransport
import io.rsocket.kotlin.transport.ktor.client.RSocketSupport
import io.rsocket.kotlin.transport.ktor.client.rSocket
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class Api(rSocket: RSocket) {
    private val proto = ConfiguredProtoBuf
    val gameApi = DefaultGameRepository(
        WsGameRepository(
            rSocket,
            proto,
            gameConfigMapper = GameConfigMapper(
                cellConfigMapper = CellConfigMapper(),
                mapConfigMapper = MapConfigMapper(),
                foodConfigMapper = FoodConfigMapper()
            )
        )
    )
}

suspend fun connectToApiUsingWS(): Api {
    val client = HttpClient {
        install(WebSockets)
        // install(JsonFeature) {
        //     serializer = KotlinxSerializer()
        //     // accept(ContentType.Application.Json)
        // }
        install(RSocketSupport) {
            connector = connector()
        }
    }

    val rSocket = client.rSocket(host = "45.77.67.171")
    return Api(rSocket)//https://45.77.67.171
}

@OptIn(InternalAPI::class)
suspend fun connectToApiUsingTCP(): Api {
    val transport = TcpClientTransport(hostname = "45.77.67.171",
        port = 8000,
        selector = SelectorManager(dispatcher = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("FAIL: $coroutineContext, $throwable")
        }))
    return Api(connector().connect(transport))
}

@OptIn(ExperimentalTime::class)
private fun connector(): RSocketConnector = RSocketConnector {
    connectionConfig {
//        setupPayload()//buildPayload { data("Test1RoomName") }
//         keepAlive = KeepAlive(
//             interval = Duration.seconds(30),
//             maxLifetime = Duration.minutes(30)
//         )

        //payload for setup frame
        setupPayload { buildPayload { data { "hello world" } } }

        //mime types
        // payloadMimeType = PayloadMimeType(
        //     data = "application/json",
        //     metadata = "application/json"
        // )
    }
}
