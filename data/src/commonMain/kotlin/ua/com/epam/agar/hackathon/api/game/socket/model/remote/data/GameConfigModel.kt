package ua.com.epam.agar.hackathon.api.game.socket.model.remote.data

import ua.com.epam.agar.hackathon.data.config.CellConfigModel
import ua.com.epam.agar.hackathon.data.config.FoodConfigModel
import ua.com.epam.agar.hackathon.data.config.MapConfigModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GameConfigModel(
    @SerialName("cell")
    val cellConfig: CellConfigModel,
    @SerialName("map")
    val mapConfig: MapConfigModel,
    @SerialName("food")
    val foodConfig: FoodConfigModel,

    @SerialName("tickTime")
    val tickTime: Long,
    @SerialName("ticksLimit")
    val tickLimit: Long
) : WebSocketData()
