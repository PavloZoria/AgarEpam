package ua.com.epam.agar.hackathon.data.cell

import ua.com.epam.agar.hackathon.data.cell.property.PositionModel
import ua.com.epam.agar.hackathon.data.cell.property.VelocityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CellModel(
    @SerialName("id")
    val cellId: String,
    @SerialName("player")
    val player: String? = null,
    @SerialName("own")
    val own: Boolean? = null,

    @SerialName("mass")
    val mass: Float? = null,
    @SerialName("radius")
    val radius: Float? = null,
    @SerialName("position")
    val position: PositionModel? = null,
    @SerialName("velocity")
    val velocity: VelocityModel? = null,

    @SerialName("canSplit")
    val canSplit: Boolean? = null,
    @SerialName("mergeTimer")
    val mergeTimer: Long? = null,
    @SerialName("canMerge")
    val canMerge: Boolean? = null,

    @SerialName("availableEnergy")
    val availableEnergy: Float? = null,

    @SerialName("speed")
    val speed: Float? = null,
    @SerialName("maxSpeed")
    val maxSpeed: Float? = null,

    @SerialName("eatEfficiency")
    val eatEfficiency: Float? = null,
    @SerialName("power")
    val power: Float? = null,

    @SerialName("del")
    val deleted: Boolean? = null
)