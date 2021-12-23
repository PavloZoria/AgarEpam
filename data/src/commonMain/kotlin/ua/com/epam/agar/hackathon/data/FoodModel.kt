package ua.com.epam.agar.hackathon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel

@Serializable
internal data class FoodModel(
    @SerialName("id")
    val id: String? = null
) {
    @SerialName("del")
    var deleted: Boolean? = null
        private set

    @SerialName("position")
    var position: PositionModel? = null
        private set

    constructor(
        id: String? = null,
        position: PositionModel? = null,
        deleted: Boolean? = null
    ) : this(id) {
        this.deleted = deleted
        this.position = position
    }

    override fun toString(): String {
        return super.toString() + "deleted: $deleted"
    }
}