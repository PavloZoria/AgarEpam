package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GrowIntentionModel(
    @SerialName("eatEfficiency")
    val eatEfficiency: Float? = null,
    @SerialName("maxSpeed")
    val maxSpeed: Float? = null,
    @SerialName("power")
    val power: Float? = null,
    @SerialName("mass")
    val mass: Float? = null,
    @SerialName("volatilization")
    val volatilization: Float? = null
)
