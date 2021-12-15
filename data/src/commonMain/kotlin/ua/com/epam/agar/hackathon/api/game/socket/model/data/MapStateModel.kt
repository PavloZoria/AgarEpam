package ua.com.epam.agar.hackathon.api.game.socket.model.data

import ua.com.epam.agar.hackathon.data.FoodModel
import ua.com.epam.agar.hackathon.data.cell.CellModel
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