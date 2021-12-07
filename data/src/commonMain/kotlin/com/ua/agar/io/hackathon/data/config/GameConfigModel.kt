package com.ua.agar.io.hackathon.data.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameConfigModel(
    @SerialName("cell")
    val cellConfig: CellConfigModel,
    @SerialName("map")
    val mapConfig: MapConfigModel,
    @SerialName("food")
    val foodConfig: FoodConfigModel
)
