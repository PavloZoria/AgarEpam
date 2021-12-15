package ua.com.epam.agar.hackathon.data.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MapConfigModel(
    @SerialName("height")
    val height: Float,
    @SerialName("width")
    val width: Float
)
