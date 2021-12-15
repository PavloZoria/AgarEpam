package ua.com.epam.agar.hackathon.data.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FoodConfigModel(
    @SerialName("mass")
    val mass: Float
)
