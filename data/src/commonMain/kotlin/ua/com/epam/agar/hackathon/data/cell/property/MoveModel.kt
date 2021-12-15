package ua.com.epam.agar.hackathon.data.cell.property

import kotlinx.serialization.Serializable

@Serializable
internal data class MoveModel(
    val velocity: VelocityModel,
)//TODO додати логіку споживання речовин?
