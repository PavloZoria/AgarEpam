package ua.com.epam.agar.hackathon.data.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickModel(@SerialName("tick") val tick: Int)