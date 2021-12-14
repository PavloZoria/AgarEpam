package com.ua.agar.io.hackathon.api.game.socket.model.data

import com.ua.agar.io.hackathon.data.CellActivityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request
 */
@Serializable
internal data class DesiredCellsStateModel(
    @SerialName("cells")
    val myCells: List<CellActivityModel>? = null,
) : WebSocketData()