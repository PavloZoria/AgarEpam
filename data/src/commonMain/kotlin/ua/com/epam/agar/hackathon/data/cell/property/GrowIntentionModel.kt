package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.Serializable

@Serializable
internal data class GrowIntentionModel(
    val eatEfficiency: Float? = null,
    val maxSpeed: Float? = null,
    val power: Float? = null,
    val mass: Float? = null
)
