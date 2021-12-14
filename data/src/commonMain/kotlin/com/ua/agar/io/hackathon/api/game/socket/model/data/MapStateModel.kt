package com.ua.agar.io.hackathon.api.game.socket.model.data

import com.ua.agar.io.hackathon.data.FoodModel
import com.ua.agar.io.hackathon.data.cell.CellModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response
 */
@Serializable
internal data class MapStateModel(
    @SerialName("tick")
    val tickNumber: Int,
    @SerialName("cells")
    val cellsOnMap: ArrayList<CellModel>,
    @SerialName("food")
    val food: ArrayList<FoodModel>,
) : WebSocketData()