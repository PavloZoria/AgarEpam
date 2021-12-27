package ua.com.epam.agar.hackathon.api.game.socket.model.remote.data

import ua.com.epam.agar.hackathon.data.cell.property.CellActivityModel
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