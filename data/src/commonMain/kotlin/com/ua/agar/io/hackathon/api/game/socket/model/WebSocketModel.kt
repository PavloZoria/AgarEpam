package com.ua.agar.io.hackathon.api.game.socket.model

import com.ua.agar.io.hackathon.api.game.socket.WebSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
internal data class WebSocketModel(
    @SerialName("key")
    val key: String,
    @SerialName("data")
    val valueJson: JsonElement? = null,
)

internal fun <T> WebSocket<WebSocketModel>.parseEntity(
    key: WebSocketKey,
    mapper: (json: JsonElement) -> T,
) = inputEvents
    .map {
        // println("WebSocket.parseEntity: for key $key and received: ${it?.key}")
        it
    }
    .filterNotNull()
    .filter { it.valueJson != null }
    // .flowOn(Dispatchers.Default)
    .filter { it.key == key.key }
    .map {
        // println("WebSocket.parseEntity: $it")
        val entity = mapper(it.valueJson!!)
        // println("WebSocket.parseEntity: parsed for key ${key.key} -> $entity")
        entity
    }