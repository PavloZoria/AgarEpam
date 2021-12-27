package ua.com.epam.agar.hackathon.api.game.socket.model.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Room(
    @SerialName("room_name")
    val roomId: String,
    @SerialName("training")
    val training: Boolean = false,
) : WebSocketData()