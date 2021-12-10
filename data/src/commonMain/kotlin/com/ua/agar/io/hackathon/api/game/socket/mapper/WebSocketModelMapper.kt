package com.ua.agar.io.hackathon.api.game.socket.mapper

import com.ua.agar.io.hackathon.api.game.socket.ktor.SocketModelMapper
import com.ua.agar.io.hackathon.api.game.socket.model.WebSocketModel
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import kotlinx.serialization.json.Json

class WebSocketModelMapper : SocketModelMapper<WebSocketModel> {
    override fun mapFrom(item: WebSocketModel): Frame.Text {
        return Frame.Text(Json.encodeToString(WebSocketModel.serializer(), item))
    }

    override fun mapTo(item: Frame.Text): WebSocketModel {
        return Json.decodeFromString(WebSocketModel.serializer(), item.readText())
    }
}