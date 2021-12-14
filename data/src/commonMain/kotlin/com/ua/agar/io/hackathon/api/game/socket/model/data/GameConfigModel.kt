package com.ua.agar.io.hackathon.api.game.socket.model.data

import com.ua.agar.io.hackathon.data.config.CellConfigModel
import com.ua.agar.io.hackathon.data.config.FoodConfigModel
import com.ua.agar.io.hackathon.data.config.MapConfigModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GameConfigModel(
    @SerialName("tickTime")
    val tickTime: Long,
    @SerialName("cell")
    val cellConfig: CellConfigModel,
    @SerialName("map")
    val mapConfig: MapConfigModel,
    @SerialName("food")
    val foodConfig: FoodConfigModel
): WebSocketData()
