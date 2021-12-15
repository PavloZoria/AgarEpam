package ua.com.epam.agar.hackathon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.com.epam.agar.hackathon.data.cell.property.PositionModel

@Serializable
internal data class FoodModel(
    @SerialName("id")
    val id: String? = null,
    @SerialName("position")
    val position: PositionModel?,
    @SerialName("del")
    val deleted: Boolean? = null,
)