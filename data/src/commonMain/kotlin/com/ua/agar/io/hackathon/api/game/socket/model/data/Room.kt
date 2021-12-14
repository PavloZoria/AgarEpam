package com.ua.agar.io.hackathon.api.game.socket.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Room(
    @SerialName("room_name")
    val roomId: String,
    @SerialName("training")
    val training: Boolean = false,
) : WebSocketData()