package ua.com.epam.agar.hackathon.api.game.socket.model.remote.data

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
    @SerialName("lastReceivedTick")
    val lastReceivedTick: Int? = null,
    @SerialName("cells")
    val cellsOnMap: Collection<CellModel>? = null,
    @SerialName("food")
    val food: Collection<FoodModel>? = null,
)/* : WebSocketData()*/ {
    override fun hashCode(): Int {
        var result = tickNumber
        result = 31 * result + (lastReceivedTick ?: 0)
        result = 31 * result + (cellsOnMap?.hashCode() ?: 0)
        result = 31 * result + (food?.hashCode() ?: 0)
        return result
    }

    //override equals method in order to compare 2 collections deeply
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MapStateModel

        if (tickNumber != other.tickNumber) return false
        if (lastReceivedTick != other.lastReceivedTick) return false
        if (other.cellsOnMap?.size != cellsOnMap?.size) return false
        if (!isEqual(cellsOnMap, other.cellsOnMap!!)) return false

        if (other.food?.size != food?.size
            || other.food != null && food == null
            || food != null && other.food == null
            || food?.containsAll(other.food!!) == false
        ) return false
        return true
    }
}

fun <T> isEqual(first: Collection<T>?, second: Collection<T>?): Boolean {
    if (first == null && second != null
        || second == null && first != null
        || first?.size != second?.size
    ) {
        return false
    }
    if (first == null && second == null) return true

    return first!!.zip(second!!).all { (x, y) -> x == y }
}